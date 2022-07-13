import React,{useState} from 'react';

export default function Textbox(props)
{
    const [text,setText] = useState("");
    const onchangeevent = (event) =>{
        setText(event.target.value);
    }
    const onClickuppercase =()=>{
        let temp = text.toUpperCase();
        setText(temp);
        props.showAlert('converted to uppercase','success');
    }
    const onClicklowercase = ()=>
    {
        let temp =text.toLowerCase();
        setText(temp);
        props.showAlert('converted to lowercase','success');

    }
    const onClickClear = () =>{
        let temp = "";
        setText(temp);
        props.showAlert('text is cleared','success');

    }
    const onclickReverse =() =>
    {
        let temp = text.split("").reverse();
        let demo = temp.join("");
        setText(demo);
        props.showAlert('text is reversed','success');

        
    }
    const onClickRemoveSpace = () =>
    {
        let temp = text.split(" ");
        let demo= temp.join("");
       // for(let i=0;i<temp.length;i++)
        //{
          //  demo +=temp[i];
        //}
        setText(demo);
        props.showAlert('spaces is removed','success');

    }
    const onClickRemoveextraspace =()=>
    {
        let temp = text.split(/[ ]+/);
        let demo = temp.join(" ");
        setText(demo);
        props.showAlert('extra spaces is removed','success');

    }
    const onClickCopy = ()=>{
       let temp = document.getElementById("mytext");
        temp.select();
        navigator.clipboard.writeText(temp.value);
        props.showAlert('text is copyed to textbox','success');

    }
   
    return (
        <>
        <div className = "container my-2" style={{color: props.mode==='dark'?'white':'black'}}>
            <h1>
                {props.heading}
            </h1>
            <div className="my-3">
                 <textarea className = "form-control" style={{backgroundColor:props.mode==='light'?'white':'black',color: props.mode==='light'?'black':'white'}} value={text} onChange={onchangeevent} id="mytext" rows="8" ></textarea>
                 <button disabled={text.length===0} className = "btn btn-primary my-2" onClick={onClickuppercase} >Convert to uppercase</button>
                 <button disabled={text.length===0} className ="btn btn-primary  my-2 mx-2" onClick={onClicklowercase}>Covert to lowercase</button>
                 <button disabled={text.length===0} className ="btn btn-primary my-2 mx-2" onClick={onclickReverse}> Reverse </button>
                 <button disabled={text.length===0} className ="btn btn-primary my-2 mx-2" onClick={onClickRemoveSpace} > Remove space </button>
                 <button disabled={text.length===0} className ="btn btn-primary my-2 mx-2" onClick={onClickRemoveextraspace}>Remove extra space</button>
                 <button disabled={text.length===0} className ="btn btn-primary my-2 mx-2" onClick={(onClickCopy)}>Copy</button>
                 <button disabled={text.length===0} className ="btn btn-primary my-2 mx-2" onClick={onClickClear}>Clear</button>
                
            </div>
        </div>
        <div className={`container text-${props.mode ==='light'?'dark':'light'} `}>
            <h2>
                Some details about text
            </h2>
            <div>
                <b> No. of words : </b>{text.split(/\s+/).filter((element)=>{return element.length !==0}).length}<p></p>
                <b> Number of character : </b>{text.length}<p></p>
                <b> Time to read : </b> {0.008*text.split(" ").filter((element)=>{return element.length !==0}).length}
            </div>
        </div>
        </>

    );
}