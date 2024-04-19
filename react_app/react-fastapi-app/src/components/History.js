import React, { useState, useEffect } from 'react';
import { Typography, Paper, List, ListItem, ListItemText, Grid } from '@mui/material';
import axios from 'axios';
import ReactMarkdown from 'react-markdown';

function History() {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/crawled-data');
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
      <Grid container spacing={2}>
        {data.map((item, index) => (
          <Grid item xs={12} md={6} key={index}>
            <Paper style={{ padding: "20px", margin: "10px" }}>
              <ReactMarkdown>
                {`### Crawled At: ${new Date(item.crawledAt).toLocaleString()}  
**URL**: ${item.url}  
**Text**: ${item.text}`}
              </ReactMarkdown>
            </Paper>
          </Grid>
        ))}
      </Grid>
    </div>
  );
}

export default History;
