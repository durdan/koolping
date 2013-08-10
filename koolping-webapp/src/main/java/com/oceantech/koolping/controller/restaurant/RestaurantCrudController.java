package com.oceantech.koolping.controller.restaurant;

import com.google.common.collect.Lists;
import com.oceantech.koolping.command.RestaurantCommand;
import com.oceantech.koolping.domain.model.restaurant.Restaurant;
import com.oceantech.koolping.domain.model.restaurant.RestaurantCuisineType;
import com.oceantech.koolping.infrastructure.persistence.restaurant.RestaurantCuisineTypeRepository;
import com.oceantech.koolping.infrastructure.persistence.restaurant.RestaurantRepository;
import com.oceantech.koolping.util.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 06/08/13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/restaurants")
public class RestaurantCrudController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantCuisineTypeRepository restaurantCuisineTypeRepository;

    @RequestMapping
    public String getUsersPage(ModelMap model) {
        Pageable pageRequest = new PageRequest(0, 10);
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageRequest);
        model.addAttribute("restaurants", RestaurantMapper.map(restaurants));
        model.addAttribute("commanduser", new RestaurantCommand());
        model.addAttribute("restaurantType", "new");
        return "restaurants";
    }


    @ModelAttribute("allCuisineType")
    public List<RestaurantCuisineType> getAllCuisineType() {
        return Lists.newArrayList(restaurantCuisineTypeRepository.findAll());
    }

    @RequestMapping(value = "/get", produces = "application/json")
    public
    @ResponseBody
    RestaurantCommand get(@RequestBody RestaurantCommand restaurantCommand) {
        return RestaurantMapper.map(restaurantRepository.findByRestaurantName(restaurantCommand.getRestaurantName()));
    }


    @RequestMapping(value = "/create", produces = "application/json", method = RequestMethod.POST)
    public String create(RestaurantCommand dto) {
        Restaurant restaurant = dto.convertToRestaurant();
        restaurantRepository.save(restaurant);
        return "redirect:/restaurants";
    }


    @RequestMapping(value = "/edit")
    public String edit(Long id, ModelMap model) {
        Pageable pageRequest = new PageRequest(0, 10);
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageRequest);
        model.addAttribute("restaurants", RestaurantMapper.map(restaurants));
        model.addAttribute("commandRestaurant", RestaurantMapper.map(restaurantRepository.findOne(id)));
        model.addAttribute("restaurantType", "update");
        return "restaurants";
    }

    @RequestMapping(value = "/delete")
    public String delete(Long id) {

        Restaurant existingRestaurant = new Restaurant();
        existingRestaurant.setRestaurantId(id);
        restaurantRepository.delete(existingRestaurant);
        return "redirect:/restaurants";
    }


}
