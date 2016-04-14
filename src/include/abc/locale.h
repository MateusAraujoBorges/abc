/* locale.h: The ABC representation of standard C library locale.h.
 * Localization <locale.h>.
 * Based on C11 Standard 7.11.
 */
#ifndef __LOCALE__
#define __LOCALE__

/* Macros,  integer constant expression */
#define LC_ALL           0
#define LC_COLLATE   	 1
#define LC_CTYPE         2
#define LC_MONETARY      3
#define LC_NUMERIC   	 4
#define LC_TIME          5     

#ifndef NULL
#define	NULL	0
#endif

struct lconv {
    char *decimal_point;      //"."          
    char *grouping;           //""           
    char *thousands_sep;      //""           

    char *mon_decimal_point;  //""           
    char *mon_grouping;       //""           
    char *mon_thousands_sep;  //""           

    char *negative_sign;      //""           
    char *positive_sign;      //""           

    char *currency_symbol;    //""           
    char frac_digits;         //CHAR_MAX     
    char n_cs_precedes;       //CHAR_MAX     
    char n_sep_by_space;      //CHAR_MAX     
    char n_sign_posn;         //CHAR_MAX     
    char p_cs_precedes;       //CHAR_MAX     
    char p_sep_by_space;      //CHAR_MAX     
    char p_sign_posn;         //CHAR_MAX     

    char *int_curr_symbol;    //""           
    char int_frac_digits;     //CHAR_MAX     
    char int_n_cs_precedes;   //CHAR_MAX     
    char int_n_sep_by_space;  //CHAR_MAX     
    char int_n_sign_posn;     //CHAR_MAX     
    char int_p_cs_precedes;   //CHAR_MAX     
    char int_p_sep_by_space;  //CHAR_MAX     
    char int_p_sign_posn;     //CHAR_MAX     
    };
    
/* Functions */  
struct  lconv *localeconv(void);
char   *setlocale(int, const char *);

#endif
