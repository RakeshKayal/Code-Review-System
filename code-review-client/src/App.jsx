import { useState } from 'react'


import { Toaster } from 'react-hot-toast'
 
import CodeReview from './Component/CodeReview'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
   <Toaster/>
    
  
    
    <CodeReview/>
    </>
  )
}

export default App
