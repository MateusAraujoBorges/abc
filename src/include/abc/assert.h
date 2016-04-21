#ifndef _ASSERT_
#define _ASSERT_
/*@ pure;
  @ depends_on \nothing;
  @ executes_when \true;
  @ */
$system void assert(_Bool expr);
#endif
