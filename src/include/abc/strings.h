/* strings.h: The ABC representation of non-standard C library.
 * strings.h is useful but non-standard strings header file.
 * Based on C11 Standard.
 */
#ifndef _STRINGS_
#define _STRINGS_

#include <stddef.h>

/* Functions */
int    bcmp(const void *, const void *, size_t);
void   bcopy(const void *, void *, size_t);
void   bzero(void *, size_t);
int    ffs(int);
char   *index(const char *, int);
char   *rindex(const char *, int);
int    strcasecmp(const char *, const char *);
int    strncasecmp(const char *, const char *, size_t);

#endif
