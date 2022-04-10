using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Coin : MonoBehaviour
{
    public float speed = 5f;
    private float leftEdge;

    private void Start()
    {
        //find screen left in word coordinates
        leftEdge = Camera.main.ScreenToWorldPoint(Vector3.zero).x - 1f; //1 unit further to make sure its funny off the screen before destroyed
        transform.position = new Vector3(transform.position.x, transform.position.y, -1);
    }

    private void Update()
    {
        transform.position += Vector3.left * speed * Time.deltaTime;

        if (transform.position.x < leftEdge)    //destroy when off-screen
        {
            Destroy(gameObject);
        }
    }

  
}
