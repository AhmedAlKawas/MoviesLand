package com.example.moviesland.view.home_page;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesland.R;
import com.example.moviesland.databinding.ItemPersonBinding;
import com.example.moviesland.interfaces.OnLastItemReached;
import com.example.moviesland.model.Person;
import com.example.moviesland.view.person_details.PersonDetailsActivity;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PersonHolder> {

    private List<Person> peopleList;
    private LayoutInflater layoutInflater;
    private Context context;
    private OnLastItemReached onLastItemReached;

    PeopleAdapter(List<Person> peopleList, OnLastItemReached onLastItemReached) {
        this.peopleList = peopleList;
        this.onLastItemReached = onLastItemReached;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        ItemPersonBinding personBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_person, parent, false);
        return new PersonHolder(personBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        holder.personBinding.setPerson(peopleList.get(position));

        if (position == peopleList.size() - 1) {
            onLastItemReached.OnLastItemReached();
        }

        holder.personBinding.cvPersonItem.setOnClickListener(view ->
                goToPersonDetails(peopleList.get(position)));

    }

    private void goToPersonDetails(Person person) {

        Intent i = new Intent(context, PersonDetailsActivity.class);
        i.putExtra(context.getResources().getString(R.string.person), person);
        context.startActivity(i);

    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    class PersonHolder extends RecyclerView.ViewHolder {

        ItemPersonBinding personBinding;

        PersonHolder(ItemPersonBinding binding) {
            super(binding.getRoot());
            personBinding = binding;
        }
    }

}
