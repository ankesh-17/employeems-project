const express= require('express')
const request=require('request')
const bodyparser = require('body-parser')

const app =  express()
const port=3000

// app.use(express.json());
// Body-parser middleware
app.use(bodyparser.urlencoded({extended:false}))
app.use(bodyparser.json())

// app.set("views", path.join(__dirname + "/views"))
app.set('view engine','ejs');

const baseURL='http://localhost:8585/employees/';  
//const baseURL='http://springapp:8585/employees/';

app.get('/',(req, res)=>{
    let url=baseURL;
    request.get(url, (err, response, body)=>{
        if (err) throw err;
        let data =JSON.parse(body);
        res.render('home', {title: 'Employee Data', records: data});
    })
})

app.get('/addEmployee', (req, res)=>{
    res.render('addEmployee', {title : 'Add employee'});
})

app.get('/employees/byid', (req, res)=>{
    let id=req.query.id;
    let url=baseURL+'byid/'+id;
    request.get(url, (err, response,body)=>{
        if (err) throw err;
        res.send(body);
    })
})

app.get('/employees/details/:id', (req, res)=>{
    let url=baseURL+'byid/'+req.params.id;
    request.get(url, (err, response, body)=>{
        if (err) throw err;
        let data= JSON.parse(body);
        res.render('details', {title: 'employee details', record: data});
    })
})

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

app.post('/employees/add', (req, res)=>{
    request.post( {
        url: baseURL+'add/',
        json: true,
        body: {
            firstName: req.body.firstName,
            lastName: req.body.lastName,
            email: req.body.email,
            salary: req.body.salary
        }
    },(err, res1, body) => {
        if (err) throw err;
        res.redirect('/');
    });
})

app.get('/employees/update/:id', (req, res)=>{
    let url=baseURL+'byid/'+ req.params.id;
    request.get(url, (err, response, body)=>{
        if (err) throw err;
        let data =JSON.parse(body);
        res.render('updateEmployee', { title : 'updatedata', record: data})
    })
})

app.post('/employees/update', (req,res)=>{
    request.put({
        url: baseURL+'update/',
        json: true,
        body: {
            id: req.body.id,
            firstName: req.body.firstName,
            lastName: req.body.lastName,
            email: req.body.email,
            salary: req.body.salary
        }
    },(err, res1, body) => {
        if (err) throw err;
        res.redirect('/');
    });
})


app.get('/employees/delete/:id', (req,res)=>{
    let id=req.params.id
    let url=baseURL+'delete/'+id;
    request.delete(url, (err, response,body)=>{
        if (err) throw err;
        res.redirect('/');
    })
})

app.listen(port, ()=>{
    console.log(`app listening at http://localhost:${port}`)
})