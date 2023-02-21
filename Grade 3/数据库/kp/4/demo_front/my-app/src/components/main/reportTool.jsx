import React, {useState} from "react";
import Button from "@material-ui/core/Button";
import {makeStyles} from "@material-ui/core/styles";
import $ from "jquery";
import Container from "@material-ui/core/Container";
import CssBaseline from "@material-ui/core/CssBaseline";
import Typography from "@material-ui/core/Typography";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import Fade from "@material-ui/core/Fade";
import TextareaAutosize from '@material-ui/core/TextareaAutosize';

const useStyles = makeStyles((theme) => ({
    modal: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    paper: {
        backgroundColor: theme.palette.background.paper,
        border: '2px solid #000',
        boxShadow: theme.shadows[5],
        padding: theme.spacing(2, 4, 3),
    },
}));
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
function ReportProblem (){
    const classes = useStylesForm();
    const problem_types = ['','UI', 'BUGS', 'SCRIPT'];
    const [state,setState] = useState({
        problem_type: '',
        description: '',
    })
    const handleChange = (event) => {
        const name = event.target.name;
        setState({
            ...state,
            [name]: event.target.value,
        });
    };
    function sendAccount(problem_type,description){
        $.ajax({
                url: "api/report",
                method:"POST",
                data:{
                    problem_type:problem_type,
                    description:description,
                    user_id:window.sessionStorage.getItem("id")
                },
                async:false,
                success:function (res){
                    if(res){
                        alert('Question accquired. It will be fixed soon.')
                    }else {
                        //dispatch(clearAccount());
                        alert('Make sure you didn\'t send an empty description or type');
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
                    Report question
                </Typography>
                <form className={classes.form} noValidate>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                onChange={handleChange}
                                variant="outlined"
                                required
                                fullWidth
                                name="description"
                                label="Description"
                                id="description"
                                autoComplete="current-description"
                            />
                        </Grid>
                        <FormControl className={classes.margin}>
                            <InputLabel id="demo-customized-select-label">problem type</InputLabel>
                            <Select
                                native
                                value={state.problem_type}
                                onChange={handleChange}
                                inputProps={{
                                    name: 'problem_type',
                                    id: 'type-native-simple',
                                }}
                            >
                                {
                                    problem_types.map(item=>(
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
                        onClick={()=>sendAccount(state.problem_type,state.description)}
                    >
                        Report question
                    </Button>
                </form>
            </div>
        </Container>
    );
}
function TransitionsModal() {
    const classes = useStyles();
    const [open, setOpen] = React.useState(false);

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <div>
            <Button type="button" onClick={handleOpen} color={"inherit"}>
                Report question
            </Button>
            <Modal
                aria-labelledby="transition-modal-title"
                aria-describedby="transition-modal-description"
                className={classes.modal}
                open={open}
                onClose={handleClose}
                closeAfterTransition
                BackdropComponent={Backdrop}
                BackdropProps={{
                    timeout: 500,
                }}
            >
                <Fade in={open}>
                    <div className={classes.paper}>
                        <ReportProblem/>
                    </div>
                </Fade>
            </Modal>
        </div>
    );
}

export default function ReportTool(){
    return(
        <TransitionsModal/>
    )
}