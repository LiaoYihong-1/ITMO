import Typography from "@material-ui/core/Typography";
import React, {useEffect, useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import $ from "jquery";

const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

function createData(name, calories, fat, carbs, protein) {
    return { name, calories, fat, carbs, protein };
}

const rows = [
    createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
    createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
    createData('Eclair', 262, 16.0, 24, 6.0),
    createData('Cupcake', 305, 3.7, 67, 4.3),
    createData('Gingerbread', 356, 16.0, 49, 3.9),
];

function BasicTable() {
    const classes = useStyles();
    const [gotten,setGotten] = useState(false);
    function getProblems(){
        $.ajax({
                url: "api/getproblems",
                method:"POST",
                data:{
                    id:window.sessionStorage.getItem("id")
                },
                async:false,
                success:function (res){
                    if(res.length > 0){
                        rows.length = 0;
                        for(var i in res){
                            if(res.is_finished) {
                                rows.push({
                                    problem_id: res[i].id,
                                    support: res[i].support_man_id,
                                    problem_type: res[i].problem_type,
                                    description: res[i].description,
                                    date: res[i].date,
                                    finished: "finished"
                                })
                            }else{
                                rows.push({
                                    problem_id: res[i].id,
                                    support: res[i].support_man_id,
                                    problem_type: res[i].problem_type,
                                    description: res[i].description,
                                    date: res[i].date,
                                    finished: "on process"
                                })
                            }
                        }
                        setGotten(true);
                    }
                }
            }
        );
    }

    useEffect(()=>{
        if(!gotten){
            getProblems()
        }
    });
    return (
        <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>Problem id</TableCell>
                        <TableCell align="right">description</TableCell>
                        <TableCell align="right">Date</TableCell>
                        <TableCell align="right">Support id</TableCell>
                        <TableCell align="right">Problem type</TableCell>
                        <TableCell align="right">status</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row) => (
                        <TableRow key={row.name}>
                            <TableCell component="th" scope="row">
                                {row.problem_id}
                            </TableCell>
                            <TableCell align="right">{row.description}</TableCell>
                            <TableCell align="right">{row.date}</TableCell>
                            <TableCell align="right">{row.support}</TableCell>
                            <TableCell align="right">{row.problem_type}</TableCell>
                            <TableCell align="right">{row.finished}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}

export default function MainReport(){
    return(
        <BasicTable/>
    )
}