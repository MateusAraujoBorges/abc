#pragma PARSE_ACSL 

int x;

/*@ reads x; */
$system int foo();

int main(){
  foo();
}
