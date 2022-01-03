const express= require('express')
const request=require('request')
const bodyparser = require('body-parser')

const app =  express()
const port=3000

// Body-parser middleware
app.use(bodyparser.urlencoded({extended:false}))
app.use(bodyparser.json())

app.set('view engine','ejs');

//const baseURL='http://localhost:8585/employees/';   
const baseURL='http://springapp:8585/employees/';


//making get request on / and returning home page with all employees records
app.get('/',(req, res)=>{
    let url=baseURL;
    request.get(url, (err, response, body)=>{
        if (err) throw err;
        let data =JSON.parse(body);
        res.render('home', {title: 'Employee Data', records: data});
    })
})

// get request on /addemployee returns a form to add employees
app.get('/addEmployee', (req, res)=>{
    res.render('addEmployee', {title : 'Add employee'});
})

//for geting employees details by id
app.get('/employees/details/:id', (req, res)=>{
    let url=baseURL+'byid/'+req.params.id;
    request.get(url, (err, response, body)=>{
        if (err) throw err;
        let data= JSON.parse(body);
        res.render('details', {title: 'employee details', record: data});
    })
})

// for getting employees by name
app.get('/employees/byname', (req, res)=>{
    let name=req.query.firstName;
    if (name==''){
        res.redirect('/');
    }else{
        let url=baseURL+'byfirstname/'+ name;
        request.get(url, (err, response,body)=>{
            if (err) throw err;
            let data= JSON.parse(body);
            res.render('home', {title:'', records: data})
        })
    }
})

//for adding employee to database
app.post('/employees/add', (req, res)=>{
    request.post( {
        url: baseURL+'add/',
        json: true,
        body: {
            firstName: req.body.firstName,
            lastName: req.body.lastName,
            email: req.body.email,
            salary: req.body.salary,
            department: req.body.department
        }
    },(err, res1, body) => {
        if (err) throw err;
        res.redirect('/');
    });
})

// for getting the update page
app.get('/employees/update/:id', (req, res)=>{
    let url=baseURL+'byid/'+ req.params.id;
    request.get(url, (err, response, body)=>{
        if (err) throw err;
        let data =JSON.parse(body);
        res.render('updateEmployee', { title : 'updatedata', record: data})
    })
})

// for updating the employee details
app.post('/employees/update', (req,res)=>{
    request.put({
        url: baseURL+'update/',
        json: true,
        body: {
            id: req.body.id,
            firstName: req.body.firstName,
            lastName: req.body.lastName,
            email: req.body.email,
            salary: req.body.salary,
            department: req.body.department
        }
    },(err, res1, body) => {
        if (err) throw err;
        res.redirect('/');
    });
})

//for deleting the employee by id
app.get('/employees/delete/:id', (req,res)=>{
    let id=req.params.id
    let url=baseURL+'delete/'+id;
    request.delete(url, (err, response,body)=>{
        if (err) throw err;
        res.redirect('/');
    })
})

//running the server on port 3000
app.listen(port, ()=>{
    console.log(`app listening at http://localhost:${port}`)
})