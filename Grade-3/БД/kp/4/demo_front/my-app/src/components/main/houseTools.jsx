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
function AddHouse() {
    const classes = useStylesForm();
    const countries = ['','US','UK','RUSSIA','CHINA','FRANCE'];
    const house_types = ['','APARTMENTS', 'VILLAS', 'HIGHEND','ORDINARY'];
    const cities = {
        '':[''],
        'CHINA':['','Shanghai', 'Beijing', 'Shenzhen', 'Guangzhou', 'Chengdu'],
        'FRANCE':['','Paris', 'Marseille', 'Lyon', 'Toulouse'],
        'UK':['','Cambridge', 'Edinburgh',  'London', 'Liverpool'],
        'US':['','New York', 'Los Angeles', 'Chicago', 'Boston'],
        'RUSSIA':['','Moscow']
    }
    const [state,setState] = useState({
        country: '',
        city: '',
        street: '',
        house_type:'',
    })
    const handleChange = (event) => {
        const name = event.target.name;
        setState({
            ...state,
            [name]: event.target.value,
        });
    };
    function sendAddress(city,country,street,house_type){
        $.ajax({
                url: "api/addhouse",
                method:"POST",
                data:{
                    city:city.replace(" ","_"),
                    country:country,
                    street:street,
                    house_type:house_type,
                    user_id:window.sessionStorage.getItem("id")
                },
                async:false,
                success:function (res){
                    if(res.success){
                        alert('success')
                    }else {
                        //dispatch(clearAccount());
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
                    Add House
                </Typography>
                <form className={classes.form} noValidate>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                onChange={handleChange}
                                variant="outlined"
                                required
                                fullWidth
                                name="street"
                                label="street"
                                id="street"
                                autoComplete="current-street"
                            />
                        </Grid>
                        <FormControl className={classes.margin}>
                            <InputLabel id="demo-customized-select-label">Country</InputLabel>
                            <Select
                                native
                                value={state.country}
                                onChange={handleChange}
                                inputProps={{
                                    name: 'country',
                                    id: 'country-native-simple',
                                }}
                            >
                                {
                                    countries.map(item=>(
                                        <option value={item}>{item}</option>
                                    ))
                                }
                            </Select>
                        </FormControl>
                        <FormControl className={classes.margin}>
                            <InputLabel id="demo-customized-select-label">City</InputLabel>
                            <Select
                                native
                                value={state.city}
                                onChange={handleChange}
                                inputProps={{
                                    name: 'city',
                                    id: 'city-native-simple',
                                }}
                            >
                                {
                                    cities[state.country].map(item=>(
                                        <option value={item}>{item}</option>
                                    ))
                                }
                            </Select>
                        </FormControl>
                        <FormControl className={classes.margin}>
                            <InputLabel id="demo-customized-select-label">House type</InputLabel>
                            <Select
                                native
                                value={state.house_type}
                                onChange={handleChange}
                                inputProps={{
                                    name: 'house_type',
                                    id: 'type-native-simple',
                                }}
                            >
                                {
                                    house_types.map(item=>(
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
                        onClick={()=>sendAddress(state.city,state.country,state.street,state.house_type)}
                    >
                        Add House
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
                Add house
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
                        <AddHouse/>
                    </div>
                </Fade>
            </Modal>
        </div>
    );
}

export default function HouseTools(){
    return(
        <TransitionsModal/>
    )
}