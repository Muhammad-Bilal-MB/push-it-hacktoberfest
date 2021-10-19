import React from 'react';
import Navbar from './Navbar';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Avatar from '@material-ui/core/Avatar';
import ProfilePic from '../Images/profile_photo.jpg'
import Grid from '@material-ui/core/Grid';
import { GitHub } from '@material-ui/icons';
import { Typography, Button } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  root: {
    // display:'flex',
    // flexWrap:'wrap',
    flexGrow: 1,
    margin: "50px"
  },
  paper: {
    textAlign: "center",
    overflow:"auto",
    // height: "auto",
    // width: "auto"
  },
  large: {
    width: theme.spacing(12),
    height: theme.spacing(12),
    // padding:"5px"
  },
  avatar: {
    paddingLeft: "20px",

  },
  
}));



const About = () => {

  const classes = useStyles();
  return (
    <div >
      <Navbar />
      <div className={classes.root} style={{ textAlign: "center" }}>
        

        <Grid container
          spacing={3}
          direction="column"
          alignItems ="center"
          justify= "center" 
          alignContent="center"
          wrap="wrap"
        >
        
          <Grid item xs={12} sm={12} md={8} xs-center = "true">
          <h1 style={{ textAlign: "center" }}>ABOUT ME</h1>
            <Paper style={{width: "100%", backgroundColor: "#c7c5f5",paddingRight:"14px",paddingLeft:"14px", paddingTop: "3px", paddingBottom: "20px" }} className={classes.paper} elevation={3}>
              <h2>Nabeel Ahmed</h2>
              <Grid container
                spacing={3}
                direction="column"
                alignItems ="center"
                justify= "center" 
                alignContent="center"
                
                 wrap="wrap"
              >
                <Grid item xs={12} sm={9} md={12} xs-center = "true" justify-xs-center ="true"></Grid>
                <Avatar alt="Remy Sharp" src={ProfilePic} className={classes.large} />
                <Grid />
              </Grid>
              <div style ={{wrap: "wrap", paddingRight: "5px"}}>
              <Typography style={{ fontWeight: "bolder", fontSize: "80%", paddingTop: "8%", paddingBottom: "5%" }}><span>"If you liked my project,don't forget to leave a <span role="img" aria-label="Emoji">⭐</span>on my repository."</span> </Typography>
              </div>
              <Button size="small" variant="contained" style = {{backgroundColor: "white", color: "black"}}>
              <a style={{ listStyle: "none",textDecoration: "none", color:"black"}} href="https://github.com/Nabeel110/Shoe-Store"><GitHub style={{ color: "black" }} fontSize="large" /><b>Github</b></a>
              </Button>
            </Paper>
          </Grid>
        </Grid>
      </div>
    </div>
  );
}

export default About;