// ===== COMMON CONFIG =====
const borrowerId = 2;   // change for demo
const ownerId = 1;

const BORROW_BASE = "http://localhost:8080/api/borrow";
const RESOURCE_BASE = "http://localhost:8080/api/resources";

// ===== LOAD AVAILABLE RESOURCES =====
function loadResources() {
    const list = document.getElementById("resourceList");
    if (!list) return; // prevents errors on other pages

    fetch(`${RESOURCE_BASE}/available`)
        .then(res => res.json())
        .then(data => {
            list.innerHTML = "";

            data.forEach(resource => {
                const li = document.createElement("li");

                li.innerHTML = `
                    <b>${resource.title}</b> (${resource.category})
                    - ${resource.description}
                    <button onclick="borrowResource(${resource.id})">Borrow</button>
                `;

                list.appendChild(li);
            });
        });
}

// ===== SEND BORROW REQUEST =====
function borrowResource(resourceId) {
    fetch(`${BORROW_BASE}/request?resourceId=${resourceId}&borrowerId=${borrowerId}&dueDate=2026-03-10`, {
        method: "POST"
    })
        .then(() => {
            alert("Borrow request sent ✅");
            loadResources();
        });
}

// ===== LOAD MY REQUESTS =====
function loadMyRequests() {
    const list = document.getElementById("myRequests");
    if (!list) return;

    fetch(`${BORROW_BASE}/my-requests?borrowerId=${borrowerId}`)
        .then(res => res.json())
        .then(data => {
            list.innerHTML = "";

            data.forEach(req => {
                const li = document.createElement("li");

                li.textContent =
                    req.resource.title + " - Status: " + req.status;

                if (req.status === "APPROVED") {
                    const btn = document.createElement("button");
                    btn.textContent = "Return";
                    btn.onclick = () => returnResource(req.id);
                    li.appendChild(btn);
                }

                list.appendChild(li);
            });
        });
}

// ===== RETURN RESOURCE =====
function returnResource(requestId) {
    fetch(`${BORROW_BASE}/return?requestId=${requestId}`, {
        method: "PUT"
    })
        .then(() => {
            alert("Returned ✅");
            loadMyRequests();
            loadResources();
        });
}

// ===== LOAD OWNER REQUESTS =====
function loadOwnerRequests() {
    const list = document.getElementById("ownerRequests");
    if (!list) return;

    fetch(`${BORROW_BASE}/owner-requests?ownerId=${ownerId}`)
        .then(res => res.json())
        .then(data => {
            list.innerHTML = "";

            data.forEach(req => {
                if (req.status === "RETURNED") return; // hide old ones for demo

                const li = document.createElement("li");

                li.textContent =
                    req.resource.title +
                    " - Requested by: " +
                    req.borrower.name +
                    " - Status: " +
                    req.status;

                if (req.status === "PENDING") {
                    const btn = document.createElement("button");
                    btn.textContent = "Approve";
                    btn.onclick = () => approveRequest(req.id);
                    li.appendChild(btn);
                }

                list.appendChild(li);
            });
        });
}

// ===== APPROVE REQUEST =====
function approveRequest(requestId) {
    fetch(`${BORROW_BASE}/approve?requestId=${requestId}`, {
        method: "PUT"
    })
        .then(() => {
            alert("Approved ✅");
            loadOwnerRequests();
        });
}