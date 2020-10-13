import React, { Component } from "react";

export default class AdminManageTimes extends Component {
  constructor(props) {
    super(props);

    this.monthNames = [
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December",
    ];
    this.today = new Date();
    this.firstDay = new Date(
      this.today.getFullYear(),
      this.today.getMonth(),
      1
    ).getDate();
    this.lastDay = new Date(
      this.today.getFullYear(),
      this.today.getMonth() + 1,
      0
    ).getDate();

    this.clickedDay = "";
    this.state = {
      showDetail: false,
      hour: "00",
      minute: "00",
      times: new Map(),
    };
  }

  toggleDetail = (day) => {
    this.clickedDay = day;
    this.setState({
      hour: "00",
      minute: "00",
      showDetail: !this.state.showDetail,
    });
  };

  hourChange = (e) => {
    this.setState({
      hour: e.target.value,
    });
  };

  minuteChange = (e) => {
    this.setState({
      minute: e.target.value,
    });
  };

  addTime = () => {
    let inputTime = this.state.hour + ":" + this.state.minute;
    let copy = new Map(this.state.times);
    let value = this.state.times.get(this.clickedDay);
    console.log(value);

    let array = value === undefined ? [] : value.slice();
    if (array.indexOf(inputTime) < 0) {
      array.push(inputTime);
    }

    copy.set(this.clickedDay, array);
    this.setState({ times: copy });
  };

  deleteTime = (e) => {
    console.log("called");
    let id = e.target.id;
    let copy = new Map(this.state.times);
    let array = this.state.times.get(this.clickedDay).slice();
    array.splice(id, 1);
    copy.set(this.clickedDay, array);
    this.setState({ times: copy });
  };

  render() {
    let listOfDates = [];
    for (let i = parseInt(this.firstDay); i <= parseInt(this.lastDay); i++) {
      listOfDates.push(
        <tr>
          <td onClick={() => this.toggleDetail(i)}>{i}</td>
        </tr>
      );
    }

    var content;

    if (this.state.showDetail) {
      if (this.state.times.get(this.clickedDay) !== undefined) {
        content = this.state.times.get(this.clickedDay).map((item, index) => (
          <div>
            {item}{" "}
            <button id={index} onClick={this.deleteTime}>
              delete
            </button>
          </div>
        ));
      } else {
        content = [];
      }

      return (
        <div>
          <h1>
            {this.monthNames[this.today.getMonth()]} {this.clickedDay}
          </h1>
          <button onClick={this.toggleDetail}>back to previou page</button>
          <br></br>
          {content}
          <br></br>
          hour:
          <select onChange={this.hourChange}>
            <option>00</option>
            <option>01</option>
            <option>02</option>
            <option>03</option>
            <option>04</option>
            <option>05</option>
            <option>06</option>
            <option>07</option>
            <option>08</option>
            <option>09</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
            <option>13</option>
            <option>14</option>
            <option>15</option>
            <option>16</option>
            <option>17</option>
            <option>18</option>
            <option>19</option>
            <option>20</option>
            <option>21</option>
            <option>22</option>
            <option>23</option>
          </select>
          minutes:
          <select onChange={this.minuteChange}>
            <option>00</option>
            <option>01</option>
            <option>02</option>
            <option>03</option>
            <option>04</option>
            <option>05</option>
            <option>06</option>
            <option>07</option>
            <option>08</option>
            <option>09</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
            <option>13</option>
            <option>14</option>
            <option>15</option>
            <option>16</option>
            <option>17</option>
            <option>18</option>
            <option>19</option>
            <option>20</option>
            <option>21</option>
            <option>22</option>
            <option>23</option>
            <option>24</option>
            <option>25</option>
            <option>26</option>
            <option>27</option>
            <option>28</option>
            <option>29</option>
            <option>30</option>
            <option>31</option>
            <option>32</option>
            <option>33</option>
            <option>34</option>
            <option>35</option>
            <option>36</option>
            <option>37</option>
            <option>38</option>
            <option>39</option>
            <option>40</option>
            <option>41</option>
            <option>42</option>
            <option>43</option>
            <option>44</option>
            <option>45</option>
            <option>46</option>
            <option>47</option>
            <option>48</option>
            <option>49</option>
            <option>50</option>
            <option>51</option>
            <option>52</option>
            <option>53</option>
            <option>54</option>
            <option>55</option>
            <option>56</option>
            <option>57</option>
            <option>58</option>
            <option>59</option>
          </select>
          <button onClick={this.addTime}>add available times </button>
        </div>
      );
    } else {
      return (
        <div>
          <h1>{this.monthNames[this.today.getMonth()]}</h1>
          <table>
            <tbody>{listOfDates}</tbody>
          </table>
        </div>
      );
    }
  }
}
