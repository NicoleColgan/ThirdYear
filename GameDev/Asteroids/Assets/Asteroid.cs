using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using TMPro;
/**
 * Author @ Nicole Colgan 19345826
 **/
public class Asteroid : MonoBehaviour
{
    public Rigidbody rb;
    public GameObject currentSpaceShip;
    public GameObject newSpaceShip;


    void Start()
    {
        //give asteroids a random scale
        transform.localScale = new Vector3(Random.Range(0.05f, 0.15f), Random.Range(0.05f, 0.15f), Random.Range
        (0.05f, 0.15f));

        rb.AddForce(new Vector3(Random.Range(-400, 400), 0, Random.Range(-400, 400)));  //random force

    }
    private void OnCollisionEnter(Collision collision)
    {
        //check what it collides with using tage
        //if it collides with the spaceship (child of the space ship rotation)
        if (collision.gameObject.tag == "SpaceShip")
        {
            collision.gameObject.transform.position = Vector3.zero; //reposition spaceship in the middle
            GameManager.lives--;
            GameObject.Find("LivesScore").GetComponent<TextMeshProUGUI>().text = GameManager.lives.ToString();  //if collided with spaceship, decriment lives
        }
        else if(collision.gameObject.tag == "Bullet")
        {
            //if asteroid was large, create number of smaller asteroids in its place
            GameManager.score += 5;
            GameObject.Destroy(collision.gameObject);   //destroy bullet
            float XScale = transform.localScale.x;
            float YScale = transform.localScale.y;
            float ZScale =  transform.localScale.z;

            //destroy doesnt happen until the end of teh current frame so you dont need to clone it here to store the value 
            GameObject asteroidCopy = this.gameObject;  //make copy of the asteroid that is about to be destroyed to use transform
            GameObject.Destroy(this.gameObject);    //destroy the asteroid
            if(XScale>=0.10f && YScale>=0.10f && ZScale >= 0.10f)   //if greater than a certain size
            {
                for (int i = 1; i <= 3; i++)    //spawn three smaller asteroids
                {
                    GameObject smallAsteroid = Instantiate(GameObject.FindGameObjectWithTag("Asteroid"));
                    smallAsteroid.transform.position = asteroidCopy.transform.position; //same position
                    smallAsteroid.transform.localScale = new Vector3(Random.Range(0.01f, 0.04f), Random.Range(0.01f, 0.04f), Random.Range(0.01f, 0.04f));   //random smaller scale
                }
            }
        }
    }




}
