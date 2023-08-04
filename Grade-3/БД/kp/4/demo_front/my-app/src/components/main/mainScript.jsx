import React, {useEffect, useState} from "react";
import Typography from "@material-ui/core/Typography";
import $ from "jquery";
import TableContainer from "@material-ui/core/TableContainer";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import Grid from "@material-ui/core/Grid";
import IconButton from "@material-ui/core/IconButton";
import KeyboardArrowUpIcon from "@material-ui/icons/KeyboardArrowUp";
import KeyboardArrowDownIcon from "@material-ui/icons/KeyboardArrowDown";
import Collapse from "@material-ui/core/Collapse";
import Box from "@material-ui/core/Box";
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import Fade from "@material-ui/core/Fade";
import {makeStyles} from "@material-ui/core/styles";
import AddAction from "./addAction";
const useRowStyles = makeStyles({
    root: {
        '& > *': {
            borderBottom: 'unset',
        },
    },

});
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
const scripts = [];
function Action(props){
    const {action} = props;
    const classes = useRowStyles();

    return (
        <React.Fragment>
            <TableRow className={classes.root}>
                <TableCell component="th" scope="row">
                    {action.id}
                </TableCell>
                <TableCell align="right">{action.action_type}</TableCell>
                <TableCell align="right">{action.target_id}</TableCell>
                <TableCell align="right">{action.target_type}</TableCell>
            </TableRow>
        </React.Fragment>
    );
}
function Script(props){
    const classes = useRowStyles();
    const formclasses = useStyles();
    const {script} = props;
    const [open, setOpen] = React.useState(false);
    const [formopen,setFormopen] = React.useState(false);
    const [getaction,setGetaction] = useState(false);
    function getAction(){
        $.ajax({
                url: "api/getActions",
                method:"POST",
                data:{
                    script_id:script.id
                },
                async:false,
                success:function (res){
                    script.action.length = 0;
                    for(var i in res){
                        script.action.push(
                            {
                                id:res[i].action_id,
                                target_id:res[i].target_id,
                                action_type:res[i].action_type,
                                target_type:res[i].target_type
                            }
                        )
                    }
                    setGetaction(true);
                }
            }
        );
    }
    useEffect(()=>{
        if(!getaction){
            getAction();
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
                <TableCell component="th">
                    {script.id}
                </TableCell>
                <TableCell align="right">{script.condition}</TableCell>
            </TableRow>
            <TableRow>
                <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                    <Collapse in={open} timeout="auto" unmountOnExit>
                        <Box margin={1}>
                            <Typography variant="h5" gutterBottom component="div">
                                Actions
                            </Typography>
                            <Button variant="contained" color="primary" size={"small"} onClick={()=>setFormopen(!formopen)}>
                                Add Action
                            </Button>
                            <Table size="small" aria-label="purchases">
                                <TableHead>
                                    <TableRow>
                                        <TableCell><Typography variant={"h6"}>Action ID</Typography></TableCell>
                                        <TableCell  align="right"><Typography variant={"h6"}>Type</Typography></TableCell>
                                        <TableCell align="right"><Typography variant={"h6"}>Furniture</Typography></TableCell>
                                        <TableCell align="right"><Typography variant={"h6"}>Furniture Type</Typography></TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {
                                        script.action.map((a)=>(
                                            <Action action={a}/>
                                            )
                                        )
                                    }
                                </TableBody>
                            </Table>
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
                                        <AddAction type="condition" script_id={script.id}/>
                                    </div>
                                </Fade>
                            </Modal>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
        </React.Fragment>
    );
}
function CollapsibleTableConditonal() {
    const [getscript,setGetscript] = useState(false);
    function getScript(){
        $.ajax({
                url: "api/getconditionscript",
                method:"POST",
                data:{
                    id:window.sessionStorage.getItem("id")
                },
                async:false,
                success:function (res){
                    scripts.length = 0;
                    for(var i in res){
                        scripts.push(
                            {
                                id:res[i].script_id,
                                condition:res[i].condition,
                                action:[]
                            }
                        )
                    }
                    setGetscript(true);
                }
            }
        );
    }
    useEffect(()=>{
        if(!getscript){
            getScript();
        }
    });
    return (
        <TableContainer component={Paper}>
            <Table aria-label="collapsible table">
                <TableHead>
                    <TableRow>
                        <TableCell/>
                        <TableCell><Typography variant={"h4"}>Id</Typography></TableCell>
                        <TableCell align="right"><Typography variant={"h4"}>Condition</Typography></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {scripts.map((script)=>(
                        <Script script={script}/>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}

export default function MainScript(){
    return(
        <Container>
            <Grid>
                <CollapsibleTableConditonal/>
            </Grid>
        </Container>
    )
}