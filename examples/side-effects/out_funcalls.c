int f(int a) {
  return a;
}
$system[funcalls] $state_f int g(int a);
$system[funcalls] int h(int b);
$abstract int k(int t);
int main() {
  int t;
  int $sef$0 = f(1);
  int $sef$1 = f(2);
  t = $sef$0 + $sef$1;
  int $sef$2 = g(1);
  int $sef$3 = g(2);
  t = $sef$2 + $sef$3;
  int $sef$4 = g(2);
  t = k(1) * k($sef$4);
  int $sef$5 = f(9);
  t = k(3) + $sef$5;
  t = k(h(t));
  int $sef$6 = f(6);
  t = k(5) + k($sef$6);
}

