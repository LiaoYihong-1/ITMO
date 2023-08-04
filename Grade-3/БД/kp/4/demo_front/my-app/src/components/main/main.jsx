import React, {useState} from 'react';
import { makeStyles,useTheme } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ReportProblemIcon from '@material-ui/icons/ReportProblem';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import MainPerson from './mainPerson';
import MainHouse from './mainHouse'
import MainScript from './mainScript'
import AccountBoxIcon from '@material-ui/icons/AccountBox';
import HomeIcon from '@material-ui/icons/Home';
import DescriptionIcon from '@material-ui/icons/Description';
import clsx from 'clsx';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import HouseTools from "./houseTools";
import MainReport from "./mainReport";
import ReportTool from "./reportTool";
import ScriptTools from "./scriptTools";
import ScheduleIcon from '@material-ui/icons/Schedule';
import MainSchedule from "./mainSchedule";
import ScheduleTools from "./ShcduleTools"
const drawerWidth = 240;


const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
    },
    appBar: {
        transition: theme.transitions.create(['margin', 'width'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
    },
    appBarShift: {
        width: `calc(100% - ${drawerWidth}px)`,
        marginLeft: drawerWidth,
        transition: theme.transitions.create(['margin', 'width'], {
            easing: theme.transitions.easing.easeOut,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    hide: {
        display: 'none',
    },
    drawer: {
        width: drawerWidth,
        flexShrink: 0,
    },
    drawerPaper: {
        width: drawerWidth,
    },
    drawerHeader: {
        display: 'flex',
        alignItems: 'center',
        padding: theme.spacing(0, 1),
        // necessary for content to be below app bar
        ...theme.mixins.toolbar,
        justifyContent: 'flex-end',
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing(3),
        transition: theme.transitions.create('margin', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        marginLeft: -drawerWidth,
    },
    contentShift: {
        transition: theme.transitions.create('margin', {
            easing: theme.transitions.easing.easeOut,
            duration: theme.transitions.duration.enteringScreen,
        }),
        marginLeft: 0,
    },
    title: {
        flexGrow: 1,
    }
}));
function PickIcon(props){
    const type = props.choice;
    if(type === 0){
        return <AccountBoxIcon/>
    }else if(type === 1){
        return <DescriptionIcon/>
    }else if(type === 3){
        return <ScheduleIcon/>
    }else{
        return <HomeIcon/>
    }
}
function PickContent(props){
    const type = props.choice;
    if(type === 'Your houses'){
        return <MainHouse/>
    }else if(type === 'Personal data'){
        return <MainPerson/>
    }else if(type === 'Report'){
        return <MainReport/>
    }else if(type === 'Schedule'){
        return <MainSchedule/>
    }
    else{
        return <MainScript/>
    }
}
function PickTool(props){
    const type = props.choice;
    if(type === 'Your houses'){
        return <HouseTools/>
    }else if(type === 'Report'){
        return <ReportTool/>
    }else if(type === 'Scripts'){
        return <ScriptTools/>
    }else if(type === 'Schedule'){
        return <ScheduleTools/>
    }
    else{
            return <box></box>
    }
}
function Desk(){
    const classes = useStyles();
    const [open, setOpen] = React.useState(false);
    const handleDrawerOpen = () => {
        setOpen(true);
    };
    const theme = useTheme();
    const handleDrawerClose = () => {
        setOpen(false);
    };
    const [content,setContent] = useState('Personal data')
    return (
        <div className={classes.root}>
            <CssBaseline />
            <AppBar
                position="fixed"
                className={clsx(classes.appBar, {
                    [classes.appBarShift]: open,
                })}
            >
                <Toolbar>
                    <IconButton
                        color="inherit"
                        aria-label="open drawer"
                        onClick={handleDrawerOpen}
                        edge="start"
                        className={clsx(classes.menuButton, open && classes.hide)}
                    >
                        <MenuIcon fontSize={"large"}/>
                    </IconButton>
                    <Typography variant="h4" className={classes.title} noWrap>
                        {content}
                    </Typography>
                    <PickTool choice={content}/>
                </Toolbar>
            </AppBar>
            <Drawer
                className={classes.drawer}
                variant="persistent"
                anchor="left"
                open={open}
                classes={{
                    paper: classes.drawerPaper,
                }}
            >
                <div className={classes.drawerHeader}>
                    <IconButton onClick={handleDrawerClose}>
                        {theme.direction === 'ltr' ? <ChevronLeftIcon /> : <ChevronRightIcon />}
                    </IconButton>
                </div>
                <Divider />
                <List>
                    {['Personal data', 'Scripts', 'Your houses','Schedule'].map((text, index) => (
                        <ListItem button key={text} onClick={()=>{setContent(text)}}>
                            <ListItemIcon>
                                <PickIcon choice={index}/>
                            </ListItemIcon>
                            <ListItemText primary={text} />
                        </ListItem>
                    ))}
                </List>
                <Divider />
                <List>
                    {['Report'].map((text, index) => (
                        <ListItem button key={text} onClick={()=>{setContent(text)}}>
                            <ListItemIcon>
                                <ReportProblemIcon/>
                            </ListItemIcon>
                            <ListItemText primary={text} />
                        </ListItem>
                    ))}
                </List>
            </Drawer>
            <main className={
                clsx(classes.content, {
                [classes.contentShift]: open,
            })}>
                <div className={classes.drawerHeader} />
                <PickContent choice={content}/>
            </main>
        </div>
    );
}
export class Main extends React.Component{

    render() {
        /*let username = window.sessionStorage.getItem("username");
        if(username===null||username===""){
            return (<div>Ah,ha. Seems that you try to bypass login</div>);
        }else {
            return (<Desk/>)
        }*/
        return(<Desk/>)
    }
}