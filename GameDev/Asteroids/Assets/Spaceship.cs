using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spaceship : MonoBehaviour
{
    private Rigidbody rb;
    public GameObject bullet;
    private int numBullets=0;
    private int maxBullets = 4;
    private bool canFire = true;
    /*
     * This script handles the parent that the spaceship is nested in.
     * The spaceship has a rotation of (90,0,0), set in the inspector, so now the y axis is pointing toward the camera
     * and the x and y axis act as a 2D x and y axis as percieved by the camera
     */
    // Start is called before the first frame update
    void Start()
    {
        
        rb = GameObject.FindGameObjectWithTag("SpaceShip").GetComponent<Rigidbody>();   //use tag to set rigidbody
        if (rb != null)
        {
            rb.angularDrag = 0.15f;
            rb.useGravity = false;
        }
    }

    // Update is called once per frame
    //fixed runs at a fixed rate - 50 p/s - whenver you want to do anything physics related, your better off using fixed update 
    //use Time.DeltaTime in update but Time.FixedDeltaTime in fixedUpdate
    void Update() //fixed update is frame rate independent
    {

        if (rb != null)
        {
            
            if (Input.GetKeyDown(KeyCode.Space) && canFire) //check if the key is pressed and the number of bullets is less than 4 p/s
            {
                StartCoroutine("CreateBullet");
                numBullets++;
            }
            if (numBullets == maxBullets)   //stop firing and reset
            {
                StartCoroutine("ResetBullets");
            }


            if (Input.GetKey("up")) //apply different force depending on mass
                rb.AddForce(transform.up * (rb.mass * Time.fixedDeltaTime * 4000f));
            // we're using an Angular Drag of 15.0 on the rigid body, so need a lot of torque here
            else if (Input.GetKey(KeyCode.LeftArrow))
            {
                Vector3 oldRotation = transform.eulerAngles;
                //rotate the parent
                transform.eulerAngles = new Vector3(oldRotation.x, oldRotation.y - (200f * Time.deltaTime), oldRotation.z);
            }
            else if (Input.GetKey(KeyCode.RightArrow))
            {
                Vector3 oldRotation = transform.eulerAngles;
                //rotate the parent
                transform.eulerAngles = new Vector3(oldRotation.x, oldRotation.y + (200f * Time.deltaTime), oldRotation.z);
            }
            else
                rb.Sleep(); //stop moving after key is released
        }
    }
    /*
     * When you call a function, it runs to completion before returning. That means tht any action taking place in a function must
     * happen within a single frame update.
     * A co-routine can pause execution during a function and return back control but then continue to where it left off in the
     * following frame.
     */
    IEnumerator CreateBullet()
    {
        //limit the number of bullet
        GameObject newBullet = Instantiate(bullet, transform.position, gameObject.transform.rotation);  //set slightly above spaceship in inspector, same rotation as spaceship
        yield return null;
    }
    //IEnumerator can pause an iteration
    IEnumerator ResetBullets()
    {
        numBullets = 0;
        canFire = false;
        //wait for 1 second before returning to finish this method
        yield return new WaitForSeconds(1);
        canFire = true;
    }

}
