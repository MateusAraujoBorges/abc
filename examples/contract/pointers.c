#include <assert.h>
#pragma PARSE_ACSL
/*@ requires x[0][0][0] == 0;
*/
int add(int *** x) 
{
  x[0][0][0] = 0;
  return x[0][0][0];
}
