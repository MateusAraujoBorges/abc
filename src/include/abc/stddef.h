/* The stddef.h header defines various variable types and macros. 
Many of these definitions also appear in other headers. */
#ifndef _STDDEF_
#define _STDDEF_
typedef unsigned long int size_t;
typedef signed long int ptrdiff_t;
typedef long int wchar_t;

#ifndef NULL
#define NULL ((void*)0)
#endif
#endif
