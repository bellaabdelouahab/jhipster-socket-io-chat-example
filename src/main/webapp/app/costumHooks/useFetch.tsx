import axios from 'axios';
import { useEffect, useState } from 'react';

const API_BASE_URL = 'http://172.22.0.1:8085';

interface FetchResponse {
  responseData: any;
  error: any;
  loading: boolean;
}

export const useFetch = (url: string): FetchResponse => {
  const [responseData, setResponseData] = useState<any>(null);
  const [error, setError] = useState<any>(null);
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        console.log('send fetch request');
        setLoading(true);
        const response = await axios.get(API_BASE_URL + url);
        setResponseData(response.data);
        console.log(response.data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [url]);

  return { responseData, error, loading };
};
