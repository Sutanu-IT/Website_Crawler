import React, { useState, useEffect } from 'react';
import { Typography, Paper, List, ListItem, ListItemText, Grid } from '@mui/material';
import axios from 'axios';
import ReactMarkdown from 'react-markdown';

function History() {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const api = 'http://localhost:30001/api/crawled-data'
        //  api is the url which call the backend api so here we have to give the suitable path for calling backend
        const response = await axios.get(api);
        setData(response.data.reverse());
      } catch (error) {
        console.error('Error fetching data: ', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div style={{ margin: "20px" }}>
      <Typography variant="h4" gutterBottom component="div">
        Crawl History
      </Typography>
      <List>
        {data.map((item, index) => (
          <ListItem key={index} alignItems="flex-start">
            <Paper style={{ padding: "20px", margin: "10px", width: "100%" }}>
              <ReactMarkdown>
                {`### Crawled At: ${new Date(item.crawledAt).toLocaleString()}  
**URL**: ${item.url}  
**Text**: ${item.text}`}
              </ReactMarkdown>
            </Paper>
          </ListItem>
        ))}
      </List>
    </div>
  );
}

export default History;
