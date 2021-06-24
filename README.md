Berkeley Db Java Edition Example

Relational Schema
- Conference (id, Nombre) [1.920 Records]
- Edition (id, ref_Conference, year, date, country) [9.600 Records]
- Researcher (id, name, surname, secSurname, university) [500.000 Records]
- Article (id, title, ref_Edition) [9.110.314 Records]
- Author (id, ref_Article, ref_Researcher) [45.561.272 Records]

Version of Berkeley Database JE
- BerkeleyDB 7.5.11

Version of Java
- Java 8 build 291

IDE
- NetBeans 8.2

Contact
- Discord: PharaJP#1912
- Instagram: @vicho_awadeuwu

How to use?
- First, open a console terminal in the root directory of the proyect
- Then, run the command *'java -jar dbgen X'*, where 'X' is the number of generations, i recomend 5
- Finally, just open the project with NetBeans or the favorite IDE and run.
