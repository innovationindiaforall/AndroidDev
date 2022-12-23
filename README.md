# AndroidDev

Fetching Data from Assets File and Loading in Section Custom Recyclerview with Search Functionality

--------------------------------------------------------------------
Fetching Data from Assets File and Loading in Section Custom Recyclerview with Search Functionality
--------------------------------------------------------------------
Requirement:
Developing Section Recyclerview with Search Functionality Features.
----------------------------------------------------------------------------------------------------
Steps:
----------------------------------------------------------------------------------------------------
we are using mainactivity page with Recyclerview

also, Fetching data from Asset Text File

then, we have to create first custom recyclerview adapter class to load section rv tiles

then, we have to declare second custom recyclerview adapter inside first recyclerview under this onBindViewHolder() method

then, load section part of list in SecondRVSectionAdapter.java adapter class

Please find the complete source code in github:

ex:

FirstRVSectionAdapter.java

 @Override
    public void onBindViewHolder(FirstSectionViewHolder holder, int position) {
    
        final RVModel sectionModel = sectionModelArrayList.get(position);
        holder.sectionLabel.setText(sectionModel.getSectionLabel());

        //recycler com.section.list.view for items
        holder.secondRecyclerView.setHasFixedSize(true);
        holder.secondRecyclerView.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.secondRecyclerView.setLayoutManager(linearLayoutManager);

        SecondRVSectionAdapter adapter = new SecondRVSectionAdapter(context, sectionModel.getItemArrayList());
        holder.secondRecyclerView.setAdapter(adapter);

    }

----------------------------------------------------------------------------------------------------
Env:
Development Language: Java

IDE: Android Studio(BumbleBee) with build Gradle Version 6.5 and Gradle Plugin version4.1.2

JDK/JRE Java11 enabled in below path

Path: AndroidStudio-File-Settings-Build,Execution,Deployment-BuildTools-Gradle-we have to select 11 version Jre file from Program Files under c folder.

added android.useAndroidX=true in gradle.properties

--------------------------------------------------------------------

https://developer.android.com/guide/navigation/navigation-deep-link

https://github.com/innovationindiaforall/AndroidDev

----------------------------------------------------------------------------------------------------

