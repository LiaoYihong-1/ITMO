import React, {useEffect, useState} from "react";
import $ from "jquery";
import Container from "@material-ui/core/Container";
import CssBaseline from "@material-ui/core/CssBaseline";
import Typography from "@material-ui/core/Typography";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import Button from "@material-ui/core/Button";
import {makeStyles} from "@material-ui/core/styles";
const useStylesForm = makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(3),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
    margin: {
        margin: theme.spacing(1),
    }
}));
const targets = [''];
export default function AddAction(props) {
    const {type,script_id} = props;
    const classes = useStylesForm();
    const action_type = ['','CLOSE','OPEN','SWITCH_OFF','SWITCH_ON'];
    const [gettarget,setGettarget] = useState(false);
    const [state,setState] = useState({
        action_type:'',
        target:0
    })
    const handleChange = (event) => {
        const name = event.target.name;
        setState({
            ...state,
            [name]: event.target.value,
        });
    };
    function getTargets(){
        $.ajax({
                url: "api/getFurnitures",
                method:"POST",
                data:{
                    id:sessionStorage.getItem("id")
                },
                async:false,
                success:function (res){
                    if(res){
                        for(var i in res){
                            targets.push(res[i]);
                        }
                    }
                    setGettarget(true);
                }
            }
        );
    }
    useEffect(()=>{
        if(!gettarget){
            getTargets();
        }
    })
    function sendAction(){
        $.ajax({
                url: "api/addaction",
                method:"POST",
                data:{
                    script_id:script_id,
                    action_type:state.action_type,
                    target:state.target,

                },
                async:false,
                success:function (res){
                    if(res.success){
                        alert('success');
                    }else{
                        alert(res.message);
                    }
                }
            }
        );
    }


    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <div className={classes.paper}>
                <Typography component="h1" variant="h5">
                    Add Action
                </Typography>
                <form className={classes.form} noValidate>
                    <Grid container spacing={2}>
                        <FormControl className={classes.margin}>
                            <InputLabel id="demo-customized-select-label">Action type</InputLabel>
                            <Select
                                native
                                value={state.furniture_type}
                                onChange={handleChange}
                                inputProps={{
                                    name: 'action_type',
                                    id: 'type-native-simple',
                                }}
                            >
                                {
                                    action_type.map(item=>(
                                        <option value={item}>{item}</option>
                                    ))
                                }
                            </Select>
                        </FormControl>
                        <FormControl className={classes.margin}>
                            <InputLabel id="demo-customized-select-label">Target id</InputLabel>
                            <Select
                                native
                                value={state.target}
                                onChange={handleChange}
                                inputProps={{
                                    name: 'target',
                                    id: 'target-native-simple',
                                }}
                            >
                                {
                                    targets.map(item=>(
                                        <option value={item}>{item}</option>
                                    ))
                                }
                            </Select>
                        </FormControl>
                    </Grid>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submit}
                        onClick={()=> {
                            sendAction()
                        }}
                    >
                        Add
                    </Button>
                </form>
            </div>
        </Container>
    );
}
