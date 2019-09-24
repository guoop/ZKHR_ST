<?php
$files = scandir('./');
$olstr = '<ol style="line-height: 2;font-size: 1.5rem;">';
foreach($files as $v){
  $olstr .= '<li><a href="'.$v.'">'.$v.'</a></li>';
}
echo $olstr . '</ol>';

