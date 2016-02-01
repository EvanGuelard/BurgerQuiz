<?php
class connector {
    $db = mysqli_connect ("127.0.0.1", "root", "", "librairie");
    mysqli_query($db, "SET NAMES UTF8");
}