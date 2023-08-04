import React, {useEffect, useState} from "react";
import Typography from "@material-ui/core/Typography";
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import Box from '@material-ui/core/Box';
import Collapse from '@material-ui/core/Collapse';
import IconButton from '@material-ui/core/IconButton';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';
import $ from "jquery";
import Button from "@material-ui/core/Button";
import Container from "@material-ui/core/Container";
import CssBaseline from "@material-ui/core/CssBaseline";
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
function AddRoom(props) {
    const classes = useStylesForm();
    const house_id = props.house_id;
    const room_types = ['','KITCHEN','BEDROOM','BATHROOM','LIVING'];
    const [state,setState] = useState({
        square: 0,
        height: 0,
        room_type:''
    })
    const handleChange = (event) => {
        const name = event.target.name;
        setState({
            ...state,
            [name]: event.target.value,
        });
    };

    function sendRoom(square,height,room_type,house_id){
        $.ajax({
                url: "api/addroom",
                method:"POST",
                data:{
                    height:height,
                    square:square,
                    room_type:room_type,
                    user_id:window.sessionStorage.getItem("id"),
                    house_id:house_id
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
                                name="square"
                                label="square"
                                id="square"
                                autoComplete="current-square"
                                type={"number"}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                onChange={handleChange}
                                variant="outlined"
                                required
                                fullWidth
                                name="height"
                                label="height"
                                id="height"
                                autoComplete="current-height"
                                type={"number"}
                            />
                        </Grid>
                        <FormControl className={classes.margin}>
                            <InputLabel id="demo-customized-select-label">Room type</InputLabel>
                            <Select
                                native
                                value={state.room_type}
                                onChange={handleChange}
                                inputProps={{
                                    name: 'room_type',
                                    id: 'type-native-simple',
                                }}
                            >
                                {
                                    room_types.map(item=>(
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
                        onClick={()=>sendRoom(state.square,state.height,state.room_type,house_id)}
                    >
                        Add Room
                    </Button>
                </form>
            </div>
        </Container>
    );
}

const useRowStyles = makeStyles({
    root: {
        '& > *': {
            borderBottom: 'unset',
        },
    },

});

function AddFurniture(props) {
    const classes = useStylesForm();
    const roomId = props.room_id;
    const furniture_type = ['','AIR_CONDITION','LIGHT', 'HUMIDIFIER', 'BATHTUB', 'OUTLET','CURTAINS', 'FAN', 'CAMERA', 'WATER_HEATER'];
    const [state,setState] = useState({
        manufacture: 0,
        furniture_type:''
    })
    const handleChange = (event) => {
        const name = event.target.name;
        setState({
            ...state,
            [name]: event.target.value,
        });
    };

    function sendFurniture(manufacture,furniture_type){
        $.ajax({
                url: "api/addfurniture",
                method:"POST",
                data:{
                    manufacture: manufacture,
                    furniture_type:furniture_type,
                    room_id:roomId
                },
                async:false,
                success:function (res){
                    if(res){
                        alert('success')
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
                    Add Furniture
                </Typography>
                <form className={classes.form} noValidate>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                onChange={handleChange}
                                variant="outlined"
                                required
                                fullWidth
                                name="manufacture"
                                label="Manufacture"
                                id="manufacture"
                                autoComplete="current-manufacture"
                            />
                        </Grid>
                        <FormControl className={classes.margin}>
                            <InputLabel id="demo-customized-select-label">Room type</InputLabel>
                            <Select
                                native
                                value={state.furniture_type}
                                onChange={handleChange}
                                inputProps={{
                                    name: 'furniture_type',
                                    id: 'type-native-simple',
                                }}
                            >
                                {
                                    furniture_type.map(item=>(
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
                        onClick={()=>sendFurniture(state.manufacture,state.furniture_type)}
                    >
                        Add furniture
                    </Button>
                </form>
            </div>
        </Container>
    );
}
function Furniture(props){
    const {furniture} = props;
    const classes = useRowStyles();

    return (
        <React.Fragment>
            <TableRow className={classes.root}>
                <TableCell component="th" scope="row">
                    {furniture.id}
                </TableCell>
                <TableCell align="right">{furniture.furniture_type}</TableCell>
                <TableCell align="right">{furniture.manufacture}</TableCell>
                <TableCell align="right">{furniture.available}</TableCell>
            </TableRow>
        </React.Fragment>
    );
}

function Room(props){
    const {room} = props;
    const classes = useRowStyles();
    const formclasses =useStyles();
    const [open, setOpen] = React.useState(false);
    const [formopen,setFormopen] = React.useState(false);
    const [funirtureaquired,setFunirtureaquired] = React.useState(false);
    function getFurniture(){
        $.ajax({
                url: "api/getfurniture",
                method:"POST",
                data:{
                    id:room.id
                },
                async:false,
                success:function (res){
                    if(res.length > 0){
                        room.furniture.length = 0;
                        for(var i in res){
                            if(res[i].available) {
                                room.furniture.push({
                                    available:"Yes",
                                    furniture_type:res[i].furniture_type,
                                    manufacture:res[i].manufacture,
                                    id:res[i].id
                                })
                            }else{
                                room.furniture.push({
                                    available:"No",
                                    furniture_type:res[i].furniture_type,
                                    manufacture:res[i].manufacture,
                                    id:res[i].id
                                })
                            }
                        }
                        setFunirtureaquired(true);
                    }
                }
            }
        );
    }
    useEffect(()=>{
        if(!funirtureaquired){
            getFurniture();
        }
    })
    return (
        <React.Fragment>
            <TableRow className={classes.root}>
                <TableCell>
                    <IconButton aria-label="expand row" size="small" onClick={() => setOpen(!open)}>
                        {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>
                </TableCell>
                <TableCell component="th" scope="row">
                    {room.id}
                </TableCell>
                <TableCell align="right">{room.square}</TableCell>
                <TableCell align="right">{room.height}</TableCell>
                <TableCell align="right">{room.room_type}</TableCell>
                <TableCell align="right">{room.is_filled}</TableCell>
            </TableRow>
            <TableRow>
                <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                    <Collapse in={open} timeout="auto" unmountOnExit>
                        <Box margin={1}>
                            <Typography variant="h5" gutterBottom component="div">
                                Furniture
                            </Typography>
                            <Button variant="contained" color="primary" size={"small"} onClick={()=>setFormopen(!formopen)}>
                                Add Furniture
                            </Button>
                            <Table size="small" aria-label="purchases">
                                <TableHead>
                                    <TableRow>
                                        <TableCell><Typography variant={"h6"}>ID</Typography></TableCell>
                                        <TableCell  align="right"><Typography variant={"h6"}>Type</Typography></TableCell>
                                        <TableCell align="right"><Typography variant={"h6"}>Manufacture</Typography></TableCell>
                                        <TableCell align="right"><Typography variant={"h6"}>Available</Typography></TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {
                                        room.furniture.map((f)=>(
                                            <Furniture furniture={f}/>
                                        ))
                                    }
                                </TableBody>
                            </Table>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
            <Modal
                aria-labelledby="transition-modal-title"
                aria-describedby="transition-modal-description"
                className={formclasses.modal}
                open={formopen}
                onClose={()=>setFormopen(false)}
                closeAfterTransition
                BackdropComponent={Backdrop}
                BackdropProps={{
                    timeout: 500,
                }}
            >
                <Fade in={formopen}>
                    <div className={formclasses.paper}>
                        <AddFurniture room_id = {room.id}/>
                    </div>
                </Fade>
            </Modal>
        </React.Fragment>
    );

}
function Row(props) {
    const { row } = props;
    const [open, setOpen] = React.useState(false);
    const [formopen,setFormopen] = React.useState(false);
    const [getroom,setGetroom] = React.useState(false);
    const classes = useRowStyles();
    const formclasses =useStyles();
    function getRoom(){
        $.ajax({
                url: "api/getroom",
                method:"POST",
                data:{
                    house_id: row.id
                },
                async:false,
                success:function (res){
                    if(res.length > 0){
                        row.rooms.length = 0;
                        for(var i in res){
                            if(res[i].is_filled) {
                                row.rooms.push({
                                    id: res[i].id,
                                    square: res[i].square,
                                    height: res[i].height,
                                    room_type: res[i].room_type,
                                    is_filled: "Fixed",
                                    furniture:[]
                                })
                            }else{
                                row.rooms.push({
                                    id: res[i].id,
                                    square: res[i].square,
                                    height: res[i].height,
                                    room_type: res[i].room_type,
                                    is_filled: "Fixing",
                                    furniture:[]
                                })
                            }
                        }
                        setGetroom(true);
                    }
                }
            }
        );
    }
    useEffect(()=>{
        if(!getroom){
            getRoom();
        }
    });
    return (
        <React.Fragment>
            <TableRow className={classes.root}>
                <TableCell>
                    <IconButton aria-label="expand row" size="small" onClick={() => setOpen(!open)}>
                        {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>
                </TableCell>
                <TableCell component="th">
                    {row.id}
                </TableCell>
                <TableCell align="right">{row.country}</TableCell>
                <TableCell align="right">{row.city}</TableCell>
                <TableCell align="right">{row.street}</TableCell>
                <TableCell align="right">{row.house_type}</TableCell>
            </TableRow>
            <TableRow>
                <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                    <Collapse in={open} timeout="auto" unmountOnExit>
                        <Box margin={1}>
                            <Typography variant="h5" gutterBottom component="div">
                                Room
                            </Typography>
                            <Button variant="contained" color="primary" size={"small"} onClick={()=>setFormopen(!formopen)}>
                                Add Room
                            </Button>
                            <Table size="small" aria-label="purchases">
                                <TableHead>
                                    <TableRow>
                                        <TableCell />
                                        <TableCell><Typography variant={"h6"}>ID</Typography></TableCell>
                                        <TableCell  align="right"><Typography variant={"h6"}>Square</Typography></TableCell>
                                        <TableCell align="right"><Typography variant={"h6"}>Height</Typography></TableCell>
                                        <TableCell align="right"><Typography variant={"h6"}>Type</Typography></TableCell>
                                        <TableCell align="right"><Typography variant={"h6"}>Is filled</Typography></TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {row.rooms.map((room) => (
                                        <Room room={room}/>
                                    ))}
                                </TableBody>
                            </Table>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
            <Modal
                aria-labelledby="transition-modal-title"
                aria-describedby="transition-modal-description"
                className={formclasses.modal}
                open={formopen}
                onClose={()=>setFormopen(false)}
                closeAfterTransition
                BackdropComponent={Backdrop}
                BackdropProps={{
                    timeout: 500,
                }}
            >
                <Fade in={formopen}>
                    <div className={formclasses.paper}>
                        <AddRoom house_id = {row.id}/>
                    </div>
                </Fade>
            </Modal>
        </React.Fragment>
    );
}

Row.propTypes = {
    row: PropTypes.shape({
        street: PropTypes.string.isRequired,
        city: PropTypes.string.isRequired,
        house: PropTypes.string.isRequired,
        history: PropTypes.arrayOf(
            PropTypes.shape({
                amount: PropTypes.number.isRequired,
                customerId: PropTypes.string.isRequired,
                date: PropTypes.string.isRequired,
            }),
        ).isRequired,
        house_type: PropTypes.string.isRequired,
        price: PropTypes.number.isRequired,
        id: PropTypes.number.isRequired,
    }).isRequired,
};

const rows = [

];

function CollapsibleTable() {
    const [gethouse,setGethouse] = useState(false);
    function getHouses(){
        $.ajax({
                url: "api/gethouses",
                method:"POST",
                data:{
                    id:window.sessionStorage.getItem("id")
                },
                async:false,
                success:function (res){
                    if(res.length > 0){
                        rows.length = 0;
                        for(var i in res){
                            rows.push({
                                id:res[i].id,
                                street:res[i].street,
                                country:res[i].country,
                                city:res[i].city,
                                house_type:res[i].house_type,
                                rooms:[]
                            })
                        }
                        setGethouse(true);
                    }
                }
            }
        );
    }
    useEffect(()=>{
        if(!gethouse){
            getHouses()
        }
    });
    return (
        <TableContainer component={Paper}>
            <Table aria-label="collapsible table">
                <TableHead>
                    <TableRow>
                        <TableCell />
                        <TableCell><Typography variant={"h4"}>Id</Typography></TableCell>
                        <TableCell align="right"><Typography variant={"h4"}>Country</Typography></TableCell>
                        <TableCell align="right"><Typography variant={"h4"}>City</Typography></TableCell>
                        <TableCell align="right"><Typography variant={"h4"}>Street</Typography></TableCell>
                        <TableCell align="right"><Typography variant={"h4"}>House type</Typography></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row)=>(
                        <Row row={row} />
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}

export default function MainHouse(){
    return(
        <CollapsibleTable/>
    )
}