
-- main category
create table categories();
$sql = "SELECT * FROM `categories` WHERE 1";

-- higher_level_sub_category
create table higher_level_sub_category (parent_id id, primary key (parent_id));
 $sql = "SELECT * FROM `higher_level_sub_category` WHERE `parent_id` = '$id' ";

 -- middle_level_sub_category
                     'id' => $row['id'],
                    'name' => $row['name'],
                    'details' => $row['description']
create table middle_level_sub_category(parent_id int, id int, name varchar(60), description varchar(120), primary key (parent_id));
$sql = "SELECT * FROM `middle_level_sub_category` WHERE `parent_id` = '$id' ";
