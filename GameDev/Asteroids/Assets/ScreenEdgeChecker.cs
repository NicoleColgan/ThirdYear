using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ScreenEdgeChecker : MonoBehaviour
{
    public Rigidbody rb;
    // Start is called before the first frame update
    void Start()
    {
        if (rb != null)
        {
            rb = this.GetComponent<Rigidbody>();

            //start periodically checking for off screen
            InvokeRepeating("checkScreenEdges", 0.1f, 0.1f);
        }
    }

    
    private void checkScreenEdges()
    {
        if (rb != null)
        {
            Vector3 pos = transform.position;
            Vector3 vel = rb.velocity;
            float xTeleport = 0f, zTeleport = 0f;

            if (pos.x < GameManager.screenBottomLeft.x && vel.x <= 0f)
                xTeleport = GameManager.screenWidth;
            else if (pos.x > GameManager.screenTopRight.x && vel.x >= 0f)
                xTeleport = -GameManager.screenWidth;
            if (pos.z < GameManager.screenBottomLeft.z && vel.z <= 0f)
                zTeleport = GameManager.screenHeight;
            else if (pos.z > GameManager.screenTopRight.z && vel.z >= 0f)
                zTeleport = -GameManager.screenHeight;
            if (xTeleport != 0f || zTeleport != 0f)
            {
                if (gameObject.tag == "Bullet")
                {
                    GameObject.Destroy(gameObject);
                }
                else
                    transform.position = new Vector3(pos.x + xTeleport, 0f, pos.z + zTeleport);
            }
        }
    }
}

