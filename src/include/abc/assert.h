#ifndef __ASSERT__
#define __ASSERT__
/*@ pure;
  @ depends \noact;
  @ guards \true;
  @ */
$system void assert(_Bool expr);
#endif
