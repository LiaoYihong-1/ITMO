import React, {useEffect, useState} from "react";
import Container from "@material-ui/core/Container";
import AccountBoxIcon from '@material-ui/icons/AccountBox';
import { positions } from '@material-ui/system';
import Box from '@material-ui/core/Box';
import Typography from "@material-ui/core/Typography";
import $ from "jquery";
import {createBrowserHistory} from "history";

export default function MainPerson(props){
    const [state,setState] = useState({
        age: '',
        gender: '',
        username: '',
        phone:'',
        email:'',
        getState:false
    })
    function getPersonalData(username,password){
        $.ajax({
                url: "api/metadata",
                method:"POST",
                data:{
                    password:password,
                    username:username
                },
                async:false,
                success:function (res){
                    if(res.success){
                        setState({
                            age: res.age,
                            gender: res.gender,
                            username: res.username,
                            phone:res.phone,
                            email:res.email,
                            getState: true
                        })
                    }else {
                        alert("Make sure that your account available or try to log in again");
                    }
                }
            }
        );
    }

    useEffect(() => {
        if(!state.getState) {
            getPersonalData(window.sessionStorage.getItem("username"), window.sessionStorage.getItem("password"));
        }
    });

    return(
        <Container >
            <Typography>
                Name:{state.username}
            </Typography>
            <Typography>
                Gender:{state.gender}
            </Typography>
            <Typography>
                Age:{state.age}
            </Typography>
            <Typography>
                Phone:{state.phone}
            </Typography>
            <Typography>
                Email:{state.email}
            </Typography>
        </Container>
    )
}