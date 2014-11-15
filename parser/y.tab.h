/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     PROGRAM = 258,
     BEGIN_ = 259,
     END = 260,
     DECLARE = 261,
     INTEGER = 262,
     BOOLEAN = 263,
     CHAR = 264,
     LABEL = 265,
     REAL = 266,
     ARRAY = 267,
     OF = 268,
     PROCEDURE = 269,
     IF = 270,
     THEN = 271,
     ELSE = 272,
     WHILE = 273,
     DO = 274,
     READ = 275,
     WRITE = 276,
     GOTO = 277,
     RETURN = 278,
     NOT = 279,
     OR = 280,
     MOD = 281,
     AND = 282,
     TRUE = 283,
     FALSE = 284,
     GE = 285,
     LE = 286,
     NE = 287,
     ASSIGN = 288,
     CHARACTER = 289,
     ID = 290,
     UNTIL = 291
   };
#endif
/* Tokens.  */
#define PROGRAM 258
#define BEGIN_ 259
#define END 260
#define DECLARE 261
#define INTEGER 262
#define BOOLEAN 263
#define CHAR 264
#define LABEL 265
#define REAL 266
#define ARRAY 267
#define OF 268
#define PROCEDURE 269
#define IF 270
#define THEN 271
#define ELSE 272
#define WHILE 273
#define DO 274
#define READ 275
#define WRITE 276
#define GOTO 277
#define RETURN 278
#define NOT 279
#define OR 280
#define MOD 281
#define AND 282
#define TRUE 283
#define FALSE 284
#define GE 285
#define LE 286
#define NE 287
#define ASSIGN 288
#define CHARACTER 289
#define ID 290
#define UNTIL 291



#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
#line 29 "gramatica.y"
{
    int number;
    char* string;
}
/* Line 1489 of yacc.c.  */
#line 124 "y.tab.h"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;

