import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Container } from '@mui/material';
import Home from './components/Home';
import History from './components/History';

function App() {
  return (
    <Router>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" style={{ flexGrow: 1 }}>
            Web Crawler App
          </Typography>
          <Link to="/" style={{ color: 'inherit', textDecoration: 'none', marginRight: 20 }}>Home</Link>
          <Link to="/history" style={{ color: 'inherit', textDecoration: 'none' }}>History</Link>
        </Toolbar>
      </AppBar>
      <Container>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/history" element={<History />} />
        </Routes>
      </Container>
    </Router>
  );
}

export default App;