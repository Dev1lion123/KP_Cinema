useEffect(() => {
    axios.get('http://localhost:8080/api/tickets')
      .then(response => setTickets(response.data))
      .catch(error => console.error('Ошибка загрузки билетов:', error));
  }, []);
  
  const addTicket = (ticket) => {
    axios.post('http://localhost:8080/api/tickets', ticket)
      .then(response => setTickets([...tickets, response.data]))
      .catch(error => console.error('Ошибка добавления билета:', error));
  };

  const deleteTicket = (id) => {
    axios.delete(`http://localhost:8080/api/tickets/${id}`)
      .then(() => setTickets(tickets.filter(ticket => ticket.id !== id)))
      .catch(error => console.error('Ошибка удаления билета:', error));
  };
  