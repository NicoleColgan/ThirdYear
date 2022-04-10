using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{
    private Vector3 direction;
    public float gravity = -9.8f;
    public float strength = 5f;
    private SpriteRenderer spriteRenderer;
    public Sprite[] sprites;
    private int spriteIndex;


    //called the first frame
    private void Start()
    {
        InvokeRepeating("AnimateSprite", 0.15f, 0.15f);     //call this method after 0.15 seconds every 0.15 seconds
    }

    //only called once when its created
    private void Awake()
    {
        spriteRenderer = GetComponent<SpriteRenderer>();
    }

    private void OnEnable()
    {
        Vector3 position = transform.position;
        position.y = 0f;
        transform.position = position;
        direction = Vector3.zero;
    }

    private void Update() //called every frame
    {
        if(Input.GetKeyDown(KeyCode.Space) || Input.GetMouseButtonDown(0))
        {
            direction = Vector3.up * strength;
        }
        //incase someones playing from mobile
        if(Input.touchCount > 0)
        {
            Touch touch = Input.GetTouch(0); //get first touch

            if(touch.phase == TouchPhase.Began) //just began touching
            {
                direction = Vector3.up * strength;
            }
        }

        //make sure were constantly applying gravity in the y direction 
        direction.y += gravity * Time.deltaTime;    //
        transform.position += direction * Time.deltaTime;   //time.delta time makes it frame rate independent
    }

    private void AnimateSprite()
    {
        spriteIndex++;

        if(spriteIndex>= sprites.Length)    //cycled through all the sprites
        {
            spriteIndex = 0;
        }

        spriteRenderer.sprite = sprites[spriteIndex];   //change the sprite to be rendered
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        
        if (other.gameObject.tag == "Pipes")
        {
            //olor colour = ColorUtility.TryParseHtmlString(#FF0000")
            other.gameObject.GetComponent<SpriteRenderer>().color = new Color(255, 0, 0);
            FindObjectOfType<GameManager>().GameOver();
        }
        else if (other.gameObject.tag == "Ground")
        {
            FindObjectOfType<GameManager>().GameOver();
        }
        else if (other.gameObject.tag == "Score")
        {
            FindObjectOfType<GameManager>().IncreaseScore();
        }
        else if (other.gameObject.tag == "YellowCoin")
        {
            FindObjectOfType<GameManager>().IncreaseCoins(1);
            Destroy(other.gameObject);
        }
        else if (other.gameObject.tag == "RedCoin")
        {
            FindObjectOfType<GameManager>().IncreaseCoins(5);
            Destroy(other.gameObject);
        }

    }

    //method to render a different set of player sprites
    public void setSprites(Sprite[] newSprites,bool yellowBird)
    {
        sprites = newSprites;
        spriteRenderer.sprite = newSprites[1];  //display new sprite immediately

        //pngs not all from same site so need different resizing
        if (!yellowBird)    
        {
            transform.localScale = new Vector3(0.05f, 0.05f, 0.05f);
        }
        else
        {
            transform.localScale = new Vector3(1f, 1f, 1f);
        }
             

    }
}
