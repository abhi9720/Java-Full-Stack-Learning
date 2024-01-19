//IIFE - it creates a private scope for the variables and functions inside it,
//  it creates a private scope for the code inside it,
// which helps prevent naming collisions with other scripts 
(function(){
	// Create table header
	var header = document.createElement('thead');
	header.classList.add('fade-in');
	
	var headerDesc = document.createElement('th');
	headerDesc.textContent = 'Description';
	header.appendChild(headerDesc);
	var headerImg = document.createElement('th');
	headerImg.textContent = 'Image';
	header.appendChild(headerImg);
	
	// Create table body
	var tablebody = document.createElement('tbody');
	tablebody.setAttribute('id', 'enrollment-data-table');
	
	// Create table element and append header and body
	var table = document.createElement('table');
	table.classList.add('table','table-hover');
	table.appendChild(header);
	table.appendChild(tablebody);
	
	
	
	// Load stored enrollment data into table
	loadData();
	
	// Submit form data on button click
	document.getElementById('enrollment-form').addEventListener('submit', function(e) {
		e.preventDefault();
		if(!validateForm()){
			return false;
		}
		// Get form data
		var name = document.getElementById('name').value;
		var email = document.getElementById('email').value;
		var website = document.getElementById('website').value;
		var image = document.getElementById('profile-photo').value;
		var gender = document.querySelector('input[name="gender-input"]:checked').value;
		var skills = [];
		var skillInputs = document.querySelectorAll('input[name="skills[]"]:checked');
		skillInputs.forEach(function(input) {
			skills.push(input.value);
		});
	
		// Create enrollment data object
		var enrollmentData = {
			name: name,
			email: email,
			website: website,
			image: image,
			gender: gender,
			skills: skills
		};
	
		// Save enrollment data to local storage
		saveData(enrollmentData);
	
		const alertMsg = document.querySelector('.alert');
		if (alertMsg) {
			alertMsg.remove();
			document.getElementById('enrollment-data').appendChild(table);
		}
	
	
		// Add enrollment data to table
		addToTable(enrollmentData);
	
		// Clear form fields
		document.getElementById('enrollment-form').reset();
	});
	
	// Save enrollment data to local storage
	function saveData(data) {
		// Get existing enrollment data from local storage
		var existingData = JSON.parse(localStorage.getItem('enrollmentData')) || [];
	
		// Add new data to existing data
		existingData.push(data);
	
		// Save updated data to local storage
		localStorage.setItem('enrollmentData', JSON.stringify(existingData));
	}
	
	// Load enrollment data from local storage and add to table
	function loadData() {
		// Get enrollment data from local storage
		var data = JSON.parse(localStorage.getItem('enrollmentData'));
	
		// if Data is not present then show msg
		if(!data){
			const msg =  document.createElement('div')
			msg.classList.add('alert','alert-primary', 'text-center');
			msg.innerHTML = '<p> No Student is Enrolled Yet </p> <p> Fill the form to enroll student</p>'
			document.getElementById('enrollment-data').appendChild(msg)
		}
		else{ // if data is present then render the table
			document.getElementById('enrollment-data').appendChild(table);
		}
	
		// Add data to table
		if (data) {
			data.forEach(function(d) {
				addToTable(d);
			});
		}
	}
	
	// Add enrollment data to table
	function addToTable(data) {
		// Create table row
		var row = document.createElement('tr');
	
		// Create description cell
		var descCell = document.createElement('td');
		descCell.innerHTML = `<strong> ${data.name } </strong> ` + '<br>' + 
						 `<span>${data.gender} </span> `  + '<br>' +
						`<span>${data.email}</span> `  + '<br>' +
						 `<a href=${data.website} class="websiteurl" target="_blank">${data.website}</a> `  + '<br>' +
						 `<p>${data.skills.join(', ')}</p> ` ;
	
		row.appendChild(descCell);
	
		// Create image cell
		var imgCell = document.createElement('td');
		var img = document.createElement('img');
		img.setAttribute('src', data.image);
		img.classList.add('info-img')
		imgCell.appendChild(img);
		row.appendChild(imgCell);
		row.classList.add('fade-in');
	
		// Add row to table
		tablebody.appendChild(row);
	}
	
	// Function to Custom validation to form 
	// This is Function right now checking for atleast one skill is selected
	function validateForm() {
		
		var checkboxes = document.getElementsByName("skills[]");
		var isChecked = false;
		for (var i = 0; i < checkboxes.length; i++) {
		  if (checkboxes[i].checked) {
			isChecked = true;
			break;
		  }
		}
		if (!isChecked) {
		  alert("Please select at least one skill.");
		  return false;
		}
		return true;
	  }
	})();