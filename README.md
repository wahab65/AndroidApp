Original App Design Project - README Template
===

# APP_NAME_HERE(Still Deciding)

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
The app is similar to any social media app like Facebook or Twitter but unlike those app, what makes this app special is that users posts deals/products that they have encountered on the internet and recommends it to other users.

For example lets suppose a user is shopping online at BestBuy and he sees an extremely amazing deal on a LG tv. The user posts the deal on the app so that other users can also can benefit from the deal and save money. 

So that question arises that why would one recommend to other users. Once a user posts a deal and when other users use that deal/link to buy the product, the user will be able to earn points. These points can be changed into money that he or she can use to buy other products on the internet through this app.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Social Media marketing app
- **Mobile:** It a mobile app.
- **Story:**
- **Market:** 
- **Habit:** Shopping
- **Scope:** A lot of untoched scope that can blow up.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can login. Once logged in, the apps saves the account and they don't have to login again.
* User can post a new deal that the public can see.
* User can view the latest 50 deals that were posted on the app.
* User can navigate through different tabs/fragments such as compose, timeline, profile.
* User can see his profile and the points he/she has earned when someone buys the product/deal the user has posted or recommended to others.
* ...

**Optional Nice-to-have Stories**

* User can search for a specific deal.
* User can pull to refresh the app to view the latest deals.
* User can like and comment on a deal/post.
* User can earn through the money: Once they post a deal, the more the people buy through that post the more the user would get points which he/she can change into money and be able to use to buy other products.
* User can see a detailed view of a deal/post
* 


### 2. Screen Archetypes

* Login
   * User is able to login to their account
   * Once logged in, the app saves the account and the user doesn't has to login again unless he logs out
* Timeline/Stream
   * User is able to view through different posts/deals that other users have posted on the app.
   * This screen allows the user to see basic information of the deal 
* Detail
    * User is able to see the details of a product/deal.
    * It also allows the user to navigate through the respective app where where the deal is from such as bestbuy. 
* Post
    * User is able to upload a new deal he or she has seen on the internet so that other users can also benefit from it
* Profile
    * User can see the posts or deals he has posted and see the points/money he has earned

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Timeline
* Post/Create
* Profile

**Flow Navigation** (Screen to Screen)

* Login
   * Timeline
* Post
   * Timeline
* Timeline
    * Detail
* Profile
    * Detail
    * Timeline

## Wireframes
<img src="https://github.com/wahab65/AndroidApp/blob/main/Wireframe.jpeg" width=600>

### [BONUS] Digital Wireframes & Mockups
<img src="https://github.com/wahab65/AndroidApp/blob/main/Digital%20Wireframe.jpg" width=600>

### [BONUS] Interactive Prototype
https://www.figma.com/proto/hfFkN4FLIG8bCJcLeAzsKv/CodePath-Wireframe-Mock?node-id=1%3A170&scaling=min-zoom&page-id=0%3A1

## Schema 

### Models

Deals

| Property  | Type | Description |
| --------- | ----- | ---------- |
| ObjectId | String  | a unique id for the deal |
| image  | File | image related to the deal that the user posts |
| Details | String | Detail of the deal |
| Before_Price | Int | The price of the deal before discount |
| After_Price | Int |  Price of the deal after discount |
| Title | String | Title of the deal |
| Brand | String | The brand/company of the deal |

### Networking

**List of network requests by screen**
- Home Feed Screen
  - (Read/GET
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
