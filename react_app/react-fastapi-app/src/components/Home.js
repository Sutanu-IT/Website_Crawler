import React, { useState } from 'react';
import { TextField, Paper,Button, Typography } from '@mui/material';
import axios from 'axios';
import ReactMarkdown from 'react-markdown';

function Home() {
  const [url, setUrl] = useState('');
  const [title, setTitle] = useState('');

  const handleSubmit = async () => {
    try {
      let api=`http://localhost:30001/api/crawl?url=${encodeURIComponent(url)}` 
      //  api is the url which call the backend api so here we have to give the suitable path for calling backend
      const response = await fetch(api);
      const data = await response.json();
      if (response.ok) {
        setTitle(data.text); 
      } else {
        throw new Error(data.message || "An error occurred");
      }
    } catch (error) {
      console.error('Error:', error);
      setTitle('Failed to crawl website');
    }
  };

  return (
    <div>
      <Typography variant="h5" gutterBottom>
        Enter a website URL to crawl
      </Typography>
      <TextField
        label="Website URL"
        variant="outlined"
        fullWidth
        value={url}
        onChange={(e) => setUrl(e.target.value)}
        style={{ marginBottom: 20 }}
      />
      <Button variant="contained" color="primary" onClick={handleSubmit}>
        Crawl Website
      </Button>
      <Typography variant="h6" style={{ marginTop: 20 }}>
      <Paper style={{ padding: "20px", margin: "10px" }}>
              <ReactMarkdown>
              {title}
              </ReactMarkdown>
            </Paper>
      </Typography>
    </div>
  );
}

export default Home;
