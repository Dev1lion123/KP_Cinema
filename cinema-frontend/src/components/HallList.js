import axios from 'axios';
import React, { useEffect, useState } from 'react';

const HallList = () => {
  const [halls, setHalls] = useState([]);

  useEffect(() => {
    const fetchHalls = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/halls');
        setHalls(response.data);
      } catch (error) {
        console.error('Ошибка при загрузке залов:', error);
      }
    };

    fetchHalls();
  }, []);

  return (
    <div>
      <h1>Список залов</h1>
      <ul>
        {halls.map((hall) => (
          <li key={hall.id}>
            {hall.name} - {hall.capacity} мест
          </li>
        ))}
      </ul>
    </div>
  );
};

export default HallList;
