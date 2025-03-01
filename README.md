# destinationQuiz

fetch("http://localhost:8080/api/users/register", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify({
        username: "testUser123",
        correctAnswers: 5,
        incorrectAnswers: 2
    })
})
.then(response => response.json())
.then(data => console.log("Response:", data))
.catch(error => console.error("Error:", error));

Uninstall existing Node.js (if installed)

Open Control Panel > Programs > Uninstall a Program
Find Node.js and uninstall it.
Download & Install nvm-windows

Go to 👉 nvm-windows GitHub Releases
Download the latest nvm-setup.exe file.
Run the installer and follow the instructions.
Restart your terminal (Command Prompt or PowerShell)
Close and reopen it so nvm is recognized.

Check if nvm is installed
Run:

sh
Copy
Edit
nvm version
If you see the version number, it’s installed correctly.

Install the latest Node.js version

sh
Copy
Edit
nvm install latest
Set the new version as active

sh
Copy
Edit
nvm use latest
Verify the installation

sh
Copy
Edit
node -v
npm -v