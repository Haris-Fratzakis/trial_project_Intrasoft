Project Description and Requirements
### Περιγραφή Άσκησης:
Σε αυτήν την άσκηση, θα δημιουργήσετε ένα σύστημα διαχείρισης λογαριασμών χρησιμοποιώντας τρία αρχεία CSV που επισυνάπτονται.
1. beneficiaries.csv : Το πεδίο beneficiaryId είναι μοναδικό για την κάθε εγγραφή.
2. accounts.csv : Το πεδίο accountId είναι μοναδικό για την κάθε εγγραφή.
3. transactions.csv : Το πεδίο transactionId είναι μοναδικό για την κάθε εγγραφή. Η μορφή της ημερομηνίας είναι μήνας/μέρα/δύο τελευταία ψηφία του έτος.
#### Να υλοποιήσετε τις παρακάτω REST διεπαφές με τις εξής προδιαγραφές:
1. Ανάκτηση στοιχείων δικαιούχου.
2. Ανάκτηση των λογαριασμών ενός δικαιούχου.
3. Ανάκτηση των συναλλαγών ενός δικαιούχου.
4. Ανάκτηση του υπολοίπου των λογαριασμών ενός ανθρώπου.
5. Ανάκτηση της μεγαλύτερης ανάληψης για έναν δικαιούχο τον τελευταίο μήνα.


Η υλοποίηση των διεπαφών πρέπει να γίνει υποχρεωτικά σε Java. Η επιλογή/χρήση framework και τρίτων βιβλιοθηκών είναι προαιρετική.

**Instructions**
The project was developed in the Jetbrains IntelliJ IDEA IDE. I used Spring boot framework and used https://start.spring.io/ to generate the project structure.

The project was built and run using the IDE. The REST endpoints were tested using Postman.

There are 5 endpoints
1) http://localhost:8080/api/beneficiaries/{beneficiaryId}. Returns the Beneficiary details in json format or null if no Beneficiary was found.
2) http://localhost:8080/api/beneficiaries/{beneficiaryId}/accounts. Returns all the Accounts of a Beneficiary in json format or an empty list if no accounts were found.
3) http://localhost:8080/api/beneficiaries/{beneficiaryId}/transactions. Returns all the Transactions for all Accounts of a Beneficiary in json format or an empty list if no transactions were found.
4) http://localhost:8080/api/beneficiaries/{beneficiaryId}/balanceByTransactions. Returns the balance of each Account of a Beneficiary (by adding deposits and subtracting withdrawals) as a List of Dictionaries in json format or an empty list if no accounts were found.
5) http://localhost:8080/api/beneficiaries/{beneficiaryId}/largestTransactionOfLastMonth. Returns the largest withdrawal Transaction for a Beneficiary in the last month (here I define last month as the month before the latest date entry in the transaction csv) or null if no withdrawal Transaction was found.
