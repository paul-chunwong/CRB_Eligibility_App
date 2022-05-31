# Personal Project of Chun Pang Wong

## Application that determines the eligibility for the Canada Recovery Benefit (CRB) during the COVID-19 Pandemic

\
*What will the application do?*

- The program can check if the user is eligible for the application of the **Canada Recovery Benefit (CRB)** during the
  COVID-19 pandemic in 2021. It adds, keeps track of all the invoices (invoices of incomes, which includes the date of
  the income, the amount of the income, the type of the income (e.g. tutoring, driving, web design), and the id of
  invoice) inputted by the user, and remove the invoice from the invoices list if needed. Possible features include
  showing all invoices, or all invoices that have a high single time earning (e.g. invoices over $1000). The application
  needs to be able to store the invoice data in a file so that it can be read for the future uses. In addition, beside
  determining the income requirement using the invoices' system, the applications will also ask the user to answer some
  simple yes or no questions that are also used to determine the final eligibility (questions such as, "Do you reside in
  Canada?", "Are you 15 years old older?"); for these yes or no questions, checkboxes will be used when designing the
  GUI. And these questions will not be in part of the user stories in Phase one, they work as add-on functions in the
  later GUI design stage in this application.

*Who will use it?*

- Anyone who is looking to apply for the **Canada Recovery Benefit (CRB)** can use this application to determine whether
  they are eligible for the funding.

*Why is this project of interest you?*

- I know that many people who are suffering financially in the COVID-19 pandemic want to gain emergency funding from the
  government (such as CRB); however, the requirements written in text from the CRB website is very complicated and
  therefore a lot of people have difficulties when determining the eligibility. Personally, I am one of the people that
  needs to apply for CRB regularly, and it will be super helpful if we can have an application that can manage our
  incomes and different status so that we can easily determine the eligibility of the funding instead of calculating the
  income requirement every month.
  
*What are the techniques used in this project?*

- Java, IntelliJ IDEA, OOP, Java Swing, Json, EventLog, Junit Test with Code Coverage 94.8%


## User Stories

**Phase ONE will focus on the invoices list system, other simple yes or no questions that determine the final CRB
eligibility will be given in the stage when designing the GUI for this application**

- As a user, I want to be able to add an invoice to the invoices list
- As a user, I want to be able to delete an invoice from my invoices list
- As a user, I want to be able to view the all the invoices from invoices list
- As a user, I want to be able to see the total earning from a specific type in my invoices list
- As a user, I want to be able to know the total number of invoice I have on my invoices list
- As a user, I want to be able to know whether the invoices list contains the invoice by entering the invoice id
- As a user, I want to be given the option to save my invoices list to file
- As a user, I want to be given the option to load my invoices list from file
- As a user, I want to check the final CRB eligibility with the checklist provided in the GUI


## Phase 4: Task 2
Fri Nov 26 02:17:23 PST 2021
Invoices are loaded!

Fri Nov 26 02:17:23 PST 2021
Added invoice (id): 123

Fri Nov 26 02:17:29 PST 2021
Added invoice (id): 234

Fri Nov 26 02:17:31 PST 2021
Removed invoice(id): 123

Fri Nov 26 02:17:33 PST 2021
Invoices cleared!

Fri Nov 26 02:17:37 PST 2021
Invoices are saved!


## Phase 4: Task 3
In terms of the class designs, I think what I have right now is the perfect design.
If I have more time doing the project, I will better fit the invoices date with the Java build-in timer so that it 
will be easier for the user to control the time range of the invoices.
