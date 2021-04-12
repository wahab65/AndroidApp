Dealio App  - A Better Deals App!
===

# App Name: Dealio (tentative)

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
- **Category:** Social Media/ E-Commerce
- **Mobile:** This app would be primarily developed for mobile but would perhaps be just as viable on a computer, such as tinder or other similar apps. Functionality wouldn't be                 limited to mobile devices, however mobile version could potentially have more features.
- **Story:** Users will post deals for consumer goods found on the internet, and user will receive  points (based on real money) as an incentive for posting deals based on the amount of clicks the person's deal gets. 
- **Market:** Any individual could choose to use this app, as it is used to purchase goods at good prices on the internet. Base market: E-Commerce, Business, Consumer Goods
- **Habit:** This app would be used as often as the individual prefers to check deals. Push notifications will also be sent to a user if their preferred deal has gone live. Addicting App.
- **Scope:** A lot of untoched scope that can blow up.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can login. Once logged in, the apps saves the account and they don't have to login again.
* User can post a new deal that the public can see.
* User can view the latest 100 deals that were posted on the app in different categories
* User can navigate through different tabs/fragments such as compose, timeline, profile.
* User can see his profile and the points he/she has earned when someone buys the product/deal the user has posted or recommended to others.
* User can have a "points" account section which directly correlates to actual money for the user.

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
   * User is also able to see the current points they have earned on the main page 
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
| image  | ParseFile | image related to the deal that the user posts |
| Details | String | Detail of the deal |
| Before_Price | String | The price of the deal before discount |
| After_Price | String |  Price of the deal after discount |
| Title | String | Title of the deal |
| Brand | String | The brand/company of the deal |
| Location | String | Where the deal is located (online, in-store, etc) |

### Networking

**List of network requests by screen**
- Home Feed Screen
  - (Read/GET) Query recent/latest Deals 
         ```
         
               public void loadTopDeals() {
                      final Deal.Query dealQuery = new Deal.Query();
                      dealQuery.getTop().withUser();
                      dealQuery.addDescendingOrder(Post.KEY_DATE);
                      dealQuery.findInBackground(new FindCallback<Post>() {
                          @Override
                          public void done(List<Deal> objects, ParseException pEx) {
                              if(pEx == null) {
                                  dealAdapter.clear();
                                  for(int i = 0; i < objects.size(); i++) {
                                      deals.add(objects.get(i));
                                      dealAdapter.notifyItemInserted(posts.size() - 1);
                                  }
                              } else {
                                  Toast.makeText(getContext(), "Failed query of deals", Toast.LENGTH_SHORT).show();
                              }
                              swipeRefreshLayout.setRefreshing(true);
                          }
                      });
                }
                   
         ```
- Post a Deal Screen
  - (Create/Post)Create a new deal for the community
    ```
      public void createDeal(String details, ParseFile image, ParseUser user, String before_price, String after_price, String brand) {
        
        final Deal newDeal = new Deal();
        newDeal.setDetails(details);
        newDeal.setImage(image);
        newDeal.setUser(user);
        newDeal.setBeforePrice(before_price);
        newDeal.setAfterPrice(after_price);
        newDeal.setBrand(brand);
        
        newDeal.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getContext(), "Deal Successfully Posted", Toast.LENGTH_SHORT).show();
                    //TODO: On a successful creation of a deal; reset/refresh the timeline fragment
                } else {
                    Toast.makeText(getContext(), "Failed to make post", Toast.LENGTH_SHORT).show();
                }
            }
        });
    } 
    ```
- Profile Screen
  - (Read/GET) GET logged in users post
     ```
        protected void queryUsersPost() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Deal.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Deals> deals, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with getting deals ", e);
                    return;
                }
                for(Deals deal: deals){
                    Log.i(TAG, "Deal: " +deal.getDescription() +", username: "+ deal.getUser().getUsername());
                }
                allDeals.addAll(deals);
                adapter.notifyDataSetChanged();
            }
        });
    }
     
     
     ```
  - (Delete) Delete a post 
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
