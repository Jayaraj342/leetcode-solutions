# Database Normalization

Normalization is the process of organizing data in a relational database to reduce redundancy and improve data integrity.

## 1NF (First Normal Form)

**Rule:** Each column contains atomic (single) values, and each row is unique.

❌ Not in 1NF

| StudentID | Name | Subjects      |
| --------- | ---- | ------------- |
| 1         | John | Math, Physics |

✅ In 1NF

| StudentID | Name | Subject |
| --------- | ---- | ------- |
| 1         | John | Math    |
| 1         | John | Physics |

## 2NF (Second Normal Form)

**Rule:**

- Must be in 1NF.
- No partial dependency: every non-key attribute must depend on the entire primary key.

Suppose:

| StudentID | CourseID | StudentName | CourseName |
| --------- | -------- | ----------- | ---------- |

Primary key = `(StudentID, CourseID)`

- `StudentName` depends only on `StudentID`.
- `CourseName` depends only on `CourseID`.

This violates 2NF.

✅ Split into:

**Students**

| StudentID | StudentName |
| --------- | ----------- |

**Courses**

| CourseID | CourseName |
| -------- | ---------- |

**Enrollments**

| StudentID | CourseID |
| --------- | -------- |

## 3NF (Third Normal Form)

**Rule:**

- Must be in 2NF.
- No transitive dependency.

❌

| EmpID | EmpName | DeptID | DeptName |
| ----- | ------- | ------ | -------- |

Primary key = `EmpID`

`DeptName` depends on `DeptID`, not directly on `EmpID`.

`EmpID → DeptID → DeptName`

✅ Split into:

**Employees**

| EmpID | EmpName | DeptID |
| ----- | ------- | ------ |

**Departments**

| DeptID | DeptName |
| ------ | -------- |

## BCNF (Boyce-Codd Normal Form)

**Rule:** For every functional dependency `X → Y`, `X` must be a super key.

BCNF is stricter than 3NF.

Example:

| Student | Course | Instructor |
| ------- | ------ | ---------- |

Dependencies:

- `(Student, Course) → Instructor`
- `Instructor → Course`

Since `Instructor` is not a super key, BCNF is violated.

Decompose into:

| Instructor | Course |
| ---------- | ------ |

| Student | Instructor |
| ------- | ---------- |

## 4NF (Fourth Normal Form)

**Rule:**

- Must be in BCNF.
- No multi-valued dependencies.

❌

| Student | Hobby | Language |
| ------- | ----- | -------- |

If hobbies and languages are independent:

John can have:

- Cricket, Chess
- English, French

This creates unnecessary combinations.

✅ Split into:

**StudentHobbies**

| Student | Hobby |
| ------- | ----- |

**StudentLanguages**

| Student | Language |
| ------- | -------- |


