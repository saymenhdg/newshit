function loadModal() {
    // Check if the modal is already loaded
    if (document.getElementById("searchResultsModal")) {
        return Promise.resolve(); // Modal is already loaded
    }

    console.log("Loading modal...");

    // Fetch the userSearch.html file from the correct path
    return fetch("/userSearch.html")  // This will correctly fetch from static folder
        .then(response => {
            if (!response.ok) {
                throw new Error(`Failed to load modal: ${response.statusText}`);
            }
            return response.text();
        })
        .then(html => {
            // Create a container for the modal
            const modalContainer = document.createElement("div");
            modalContainer.innerHTML = html;
            document.body.appendChild(modalContainer);

            // Add event listener for closing the modal
            const closeModal = document.getElementById("closeModal");
            if (closeModal) {
                closeModal.addEventListener("click", () => {
                    document.getElementById("searchResultsModal").style.display = "none";
                });
            }
        })
        .catch(error => console.error("Error loading modal:", error));
}


function searchUsers() {
    const query = document.getElementById("searchInput").value;
    if (!query) {
        alert("Please enter a username to search.");
        return;
    }

    fetch(`/api/users/search?query=${query}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            // Load the modal dynamically
            loadModal().then(() => {
                const resultsList = document.getElementById("resultsList");
                resultsList.innerHTML = ""; // Clear previous results

                if (data.length === 0) {
                    resultsList.innerHTML = "<li>No users found.</li>";
                } else {
                    data.forEach(user => {
                        const listItem = document.createElement("li");
                        listItem.textContent = `${user.firstName} ${user.lastName} (@${user.username})`;
                        listItem.onclick = () => visitUserProfile(user.id);
                        resultsList.appendChild(listItem);
                    });
                }

                // Show the modal
                document.getElementById("searchResultsModal").style.display = "block";
            });
        })
        .catch(error => console.error("Error fetching users:", error));
}
