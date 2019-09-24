<?php
$username = $_REQUEST['username'];

if($username === 'yewuyuan'){
  $data = [
    'code' => 0,
    'post' => [
      [
        'postCode' => 'shaoshou',
      ]
    ],
  ];
}elseif( $username === 'shiyanshi'){
  $data = [
    'code' => 0,
    'post' => [
      [
        'postCode' => 'shiyanshi',
      ]
    ],
  ];
}elseif( $username === 'banzhuren'){
  $data = [
    'code' => 0,
    'post' => [
      [
        'postCode' => 'shiyanshi',
      ],
      [
        'postCode' => 'banzhuren',
      ]
    ],
  ];
}

echo json_encode($data);