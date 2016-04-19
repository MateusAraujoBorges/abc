#ifndef __ASSERT__
#define __ASSERT__
/*@ pure;
  @ depends_on \nothing;
  @ executes_when \true;
  @ */
$system void assert(_Bool expr);
#endif
