<?php
$t1 = microtime(true);
/*
 * this class is make sure that all number presented by scientific notation only keep two decimal places
 */
class StringFormatter
{
    var $second = "";//number after letter e
    var $first = "";//number before letter e
    var $result = "";

    function format($str)
    {
        //make sure that number is presented by scientific notation, or it will do nothing
        if (preg_match("/^.*E.*$/i", $str)) {
            //split the number before letter e and save it
            if (preg_match("/(.*\.\d\d)/", $str, $matches)) {
                $this->first = $matches[1];
                //split the number after letter e and save it
                if (preg_match("/(e.*$)/i", $str, $matches1)) {
                    $this->second = $matches1[0];
                }
                $this->result = $this -> first . $this->second;
            } else {
                $this->result = $str;
            }
        } else {
            $this->result = $str;
        }
        return $this->result;
    }
}

/*
 * distinct that coordinates in the range or not
 */
class Checker
{
    var $x;
    var $y;
    var $R;
    var $suit;

    function __construct($x,$y,$R){
        $this->R = $R;
        $this->y = $y;
        $this->x = $x;
    }

    function setR($R){
        $this->R = $R;
    }

    function check()
    {
        if($this->x <=0 && $this ->x >= -$this->R && $this->y <= $this->R && $this->y >= -($this->R+$this->x)/2){
            return true;
        }else if( ($this->x > 0) && pow($this->R,2) > (pow($this->x,2)+pow($this->y,2)) ){
            return true;
        }
        return false;
    }
}

$x = $y ="";
$R = array();
$formatter = new StringFormatter;

if($_SERVER["REQUEST_METHOD"] == "POST") {
    $x = $_POST["x"];
    $y = $_POST["y"];
    $R = $_POST["R"];
    if (intval($y) >= 3 || intval($y) <= -5) {
        echo "Another data check on Server!";
    } else {
        $checker = new Checker($x, $y, 1);
        $table = "<table border='1'>";
        $table .= "<tr>";
        $table .= "<td>x</td>";
        $table .= "<td>y</td>";
        $table .= "<td>R</td>";
        $table .= "<td>Result</td>";
        $table .= "</tr>";

        for ($i = 0; $i < count($R); ($i++)) {
            $checker->setR($R[$i]);
            $table .= "<tr>";
            $table .= "<td>$x</td>";
            $table .= "<td>$y</td>";
            $table .= "<td>$R[$i]</td>";
            if ($checker->check()) {
                $table .= "<td>Coordinate in range</td>";
            } else {
                $table .= "<td>Coordinate out of range</td>";
            }
            $table .= "</tr>";
        }
        echo "<br>";
        echo $table;
        echo date("Y - M - d, H : i");
        echo "<br>";
        echo "This script run " . $formatter->format((microtime(true) - $t1)) . "s";
    }
}