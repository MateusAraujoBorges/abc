//======================= funcalls.c =======================
int f(int a)
{
  return a;
}
/*@ pure;
  @*/
$system[funcalls] int g(int a);
$abstract int k(int t);
int main()
{
  int t;
  int $sef$0 = f(1);
  int $sef$1 = f(2);
  t = $sef$0 + $sef$1;
  t = g(1) + g(2);
  t = k(1) * k(g(2));
  int $sef$2 = f(9);
  t = k(3) + $sef$2;
  int $sef$3 = f(6);
  t = k(5) + k($sef$3);
}
